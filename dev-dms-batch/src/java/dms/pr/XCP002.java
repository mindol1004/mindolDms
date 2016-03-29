package dms.pr;

import java.util.HashMap;
import java.util.Map;

import nexcore.framework.bat.IBatchContext;
import nexcore.framework.bat.base.AbsBatchComponent;
import nexcore.framework.bat.base.AbsRecordHandler;
import nexcore.framework.bat.base.DBSession;
import nexcore.framework.core.data.DataSet;
import nexcore.framework.core.data.IDataSet;
import nexcore.framework.core.data.IOnlineContext;
import nexcore.framework.core.data.IRecord;
import nexcore.framework.core.data.IRecordSet;
import nexcore.framework.core.exception.BizRuntimeException;
import nexcore.framework.core.util.DateUtils;
import nexcore.framework.core.util.StringUtils;

import org.apache.commons.logging.Log;

/**
 * <ul>
 * <li>업무 그룹명 : dms/임대R</li>
 * <li>서브 업무명 : XCP002</li>
 * <li>설  명 : <pre>[PR]임대단말매각</pre></li>
 * <li>작성일 : 2015-08-11 13:09:10</li>
 * <li>작성자 : 김상오 (myneti99)</li>
 * </ul>
 */
public class XCP002 extends AbsBatchComponent {
	 	private Log log;
	    private boolean delFlag = false;
	    private int processCnt = 0;
	    private String taskNo = "";
	    private String procFileName = "";
	    
	    /**
	     * 생성자
	     */
	    public XCP002() {
	        super();
	    }

	    /**
	     * 배치 전처리 메소드. 
	     * 여기서 Exception 발생시 execute() 메소드는 실행되지 않고, afterExecute() 는 실행됨
	     */
	    public void beforeExecute(IBatchContext context) {
	    	Log log = getLog(context);

			processCnt = 0;
			taskNo = "";
			procFileName = "";
	
			IOnlineContext onlineCtx = makeOnlineContext(context);
			IDataSet reqDS = new DataSet();
			IDataSet resDS = callOnlineBizComponent("sc.SCSBase", "fInqTaskNoSeq",
					reqDS, onlineCtx);
			taskNo = resDS.getField("TASK_NO");
	
			reqDS.putField("TASK_DT", DateUtils.getCurrentDate());
			reqDS.putField("TASK_ID", context.getInParameter("TASK_ID"));
			reqDS.putField("TASK_NO", taskNo);
			reqDS.putField("TASK_NM", context.getInParameter("TASK_NM"));
			reqDS.putField("GRP_ID", "PR");
			reqDS.putField("INST_CD", "DMS");
			reqDS.putField("BAT_TASK_PROC_ST_CD", "B");
			reqDS.putField("PROC_CNT", "0");
			reqDS.putField("FS_REG_USER_ID", "BAT");
			reqDS.putField("LS_CHG_USER_ID", "BAT");
	
			callOnlineBizComponent("sc.SCSBase", "fRegBatTaskOpHst", reqDS,
					onlineCtx);
	
			log = getLog(context);
			if (log.isDebugEnabled()) {
				log.debug("XCP002(임대단말매각)_컴포넌트 호출 결과:");
				log.debug(resDS);
			}
	    }

	    /**
	     * 배치 메인 메소드
	     */
	    public void execute(final IBatchContext context) {
	       log = getLog(context);
//	       log.debug("\n\n >>>>>>>>>>>  임대단말매각 배치 실행    >>>>>>>>>>>>>> ");
	        IOnlineContext ctx =  getOnlineContextFromOndemand();
//	        log.debug(">>>>>>>>>>>>>>>   IN_DT : "+context.getInParameter("IN_DT"));
//	        log.debug(">>>>>>>>>>>>>>>   XCL_GUBUN : "+context.getInParameter("XCL_GUBUN"));
	        
	        Map<String, String> param = new HashMap<String, String>();
	        param.put("IN_DT", context.getInParameter("IN_DT"));
	        
	        // 트랜잭션 시작
	    	txBegin();  
	    	dbStartSession();
	    	dbBeginBatch();
	        
	    	DBSession readSession = dbNewSession(context, false, null);
	    	try {
	    		//매각
	    	    if("AS".equals(context.getInParameter("XCL_GUBUN"))){
//	    	    	제각상세
	    	    	dbSelect("SXclAssetAs", param, makeRecordHandler(context), readSession);
	    	    	
	    	    	log.debug(">>>>>>>>>>>>>>>  매각 그룹 호출 ");
	    	    	//제각마스터
	    	    	dbSelect("SXclAssetAsGrp", param, makeRecordSuperHandler(context), readSession);
	    	    	log.debug(">>>>>>>>>>>>>>>  매각 그룹 호출 완료");
	    	    }	    	    
	    	} catch (Exception e) {
	            throw new BizRuntimeException("M00001", e);
	        }
	        
			// 트랜잭션 커밋
			dbEndBatch();
			dbEndSession();
			txCommit();
	        
	      
	    }
	    
	    /**
	     * 배치 후처리 메소드. 
	     * beforeExecute(), execute() 의 Exception 발생 여부와 관계없이 이 메소드는 실행됨
	     */
	    public void afterExecute(IBatchContext context) {
	    	IOnlineContext onlineCtx = makeOnlineContext(context);
			IDataSet reqDS = new DataSet();
			reqDS.putField("TASK_NO", taskNo);
			reqDS.putField("PROC_FILE_NM", procFileName);
			reqDS.putField("LS_CHG_USER_ID", "BAT");
			if (super.exceptionInExecute == null) {
				// execute() 정상인 경우
				reqDS.putField("BAT_TASK_PROC_ST_CD", "S");
			} else {
				// execute() 에서 에러 발생할 경우
				reqDS.putField("BAT_TASK_PROC_ST_CD", "F");
				processCnt = 0;
			}
			reqDS.putField("PROC_CNT", "" + processCnt);
			IDataSet resDS = callOnlineBizComponent("sc.SCSBase", "fUpdBatTaskOpHst",
					reqDS, onlineCtx);
	
			Log log = getLog(context);
			if (log.isDebugEnabled()) {
				log.debug("XCP002(임대단말매각)_컴포넌트 호출 결과:");
				log.debug(resDS);
			}
	    }
	    
	    /**
	     * 임대단말매각
	     * 
	     */
	    public AbsRecordHandler makeRecordHandler(IBatchContext context) {
	    	AbsRecordHandler rh = new AbsRecordHandler(context) {
	    		IRecordSet rs = null;
	    		IRecord rd = null;
				@Override
				public void handleRecord(IRecord row) {
					Map<String,Object> map = new HashMap<String, Object>();
					//등록될 정보 셋팅
					
					map.put("USER_NO", context.getInParameter("USER_NO"));
					
					map.put("ASSET_DISPO_STRD_YM"   , row.get("ASSET_DISPO_STRD_YM"   )  );
					map.put("OP_CL_CD"   , row.get("OP_CL_CD"   )  );
					map.put("DSPSL_DIS_CL"          , row.get("DSPSL_DIS_CL"          )  );
					
					map.put("ASSET_DISPO_DEPT_CD"   , row.get("OP_CL_CD"   )  );
					map.put("ASSET_DISPO_ACNTB_AMT" , StringUtils.nvlEmpty(row.get("ASSET_DISPO_ACNTB_AMT" ), "0")   );
					map.put("ASSET_DISPO_REM_AMT"   , StringUtils.nvlEmpty(row.get("ASSET_DISPO_REM_AMT"   ), "0")   );
					map.put("DISPO_AMT"             , StringUtils.nvlEmpty(row.get("DISPO_AMT"             ), "0")   );
					map.put("DISPO_PRFITLS_AMT"     , StringUtils.nvlEmpty(row.get("DISPO_PRFITLS_AMT"     ), "0")   );
					map.put("ASSET_DISPO_SLIP_NO"   , row.get("ASSET_DISPO_SLIP_NO"   )  );
					map.put("ASSET_DISPO_SLIP_DT"   , row.get("ASSET_DISPO_SLIP_DT"   )  );
					map.put("ASSET_NO"   , row.get("ASSET_NO"   )  );

					
//					log.debug(processCnt+">>>>>>>>>>>>>>>>>>>>   DISPO_AMT: "+row.get("DISPO_AMT"             ));
//					log.debug(processCnt+">>>>>>>>>>>>>>>>>>>>   nvlEmpty: "+StringUtils.nvlEmpty(row.get("DISPO_AMT"), "0"));
					log.debug(processCnt+">>>>>>>>>>>>>>>>>>>>   map: "+map);
					//매각
					if("AS".equals(context.getInParameter("XCL_GUBUN"))){
						//삭제후 처리
						if(delFlag == false ){
							rs = dbSelectMulti("SAssetDispoSlipCheck", map, context);
							if(rs != null){

								for (int i = 0; i < rs.getRecordCount(); i++) {
									rd = rs.getRecord(i);
									//전표번호가 없는 데이터 삭제
									if(StringUtils.isEmpty(rd.get("SLIP_NO")) ){
										dbDelete("DAssetDispoGrp", rd);
										dbDelete("DAssetDispoDtl", rd);
									}
									//전표번호가 있으나  미발행, 전표삭제 상태일경우 삭제
									else if("00".equals(rd.get("SLIP_ST_CD")) || "05".equals(rd.get("SLIP_ST_CD")) ){
										dbDelete("DAssetDispoGrp", rd);
										dbDelete("DAssetDispoDtl", rd);
									}
								}
							}
							delFlag = true;
						}
						//매각 상세 등록	
						dbInsert("IXclAssetAsDtl", map);
					}
					
					processCnt++;
				}
			};
	    	return rh;
	    }
	    
	    /**
	     * 임대단말매각그룹 처리
	     * 
	     */
	    public AbsRecordHandler makeRecordSuperHandler(IBatchContext context) {
	    	AbsRecordHandler rh = new AbsRecordHandler(context) {
	    		
	    		
	    		
				@Override
				public void handleRecord(IRecord row) {
					Map<String,Object> map = new HashMap<String, Object>();
					//등록될 정보 셋팅
					log.debug(">>>>>>>>>>>>>>>  처리  함수 진입 ");
					map.put("USER_NO", context.getInParameter("USER_NO"));
					
					map.put("ASSET_DISPO_STRD_YM"   , row.get("ASSET_DISPO_STRD_YM"   )  );
					map.put("ASSET_DISPO_DEPT_CD"   , row.get("OP_CL_CD"   )  );
					map.put("OP_CL_CD"   , row.get("OP_CL_CD"   )  );
					map.put("DSPSL_DIS_CL"          , row.get("DSPSL_DIS_CL"          )  );
					map.put("ASSET_DISPO_ACNTB_AMT" , StringUtils.nvlEmpty(row.get("ASSET_DISPO_ACNTB_AMT" ), "0")   );
					map.put("ASSET_DISPO_REM_AMT"   , StringUtils.nvlEmpty(row.get("ASSET_DISPO_REM_AMT"   ), "0")   );
					map.put("DISPO_AMT"             , StringUtils.nvlEmpty(row.get("DISPO_AMT"             ), "0")   );
					map.put("DISPO_PRFITLS_AMT"     , StringUtils.nvlEmpty(row.get("DISPO_PRFITLS_AMT"     ), "0")   );
					map.put("ASSET_DISPO_SLIP_NO"   , row.get("ASSET_DISPO_SLIP_NO"   )  );
					map.put("ASSET_DISPO_SLIP_DT"   , row.get("ASSET_DISPO_SLIP_DT"   )  );

//					log.debug(processCnt+">>>>>>>>>>>>>>>>>>>>   DISPO_AMT: "+row.get("DISPO_AMT"             ));
//					log.debug(processCnt+">>>>>>>>>>>>>>>>>>>>   nvlEmpty: "+StringUtils.nvlEmpty(row.get("DISPO_AMT"), "0"));
					log.debug(processCnt+">>>>>>>>>>>>>>>>>>>>   map: "+map);

					//매각 처리
					if("AS".equals(context.getInParameter("XCL_GUBUN"))){
						//등록 수정 처리 
						dbInsert("IXclAssetAsGrp", map);
			        	 
			        }
					
					processCnt++;
				}
			};
	    	return rh;
	    }

	}
