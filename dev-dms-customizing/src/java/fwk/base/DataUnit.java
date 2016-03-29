package fwk.base;

import java.util.List;

import nexcore.framework.core.data.IOnlineContext;
import nexcore.framework.core.data.IRecord;
import nexcore.framework.core.data.IRecordHeader;
import nexcore.framework.core.data.IRecordSet;
import nexcore.framework.core.service.internal.OnlineContextHelper;
import nexcore.framework.integration.db.internal.ResultSetColumnNameHelper;
import nexcore.framework.integration.ibatis.IFinishRowHandler;
import nexcore.framework.integration.ibatis.NcRowHandler;

public class DataUnit extends nexcore.framework.coreext.pojo.biz.DataUnit {

    protected final IRecordSet dbSelect(String stmtName, Object param, IHpcRecordResolver recordResolver, IOnlineContext onlineCtx) {
       return  dbSelect(stmtName, param, null, recordResolver,  onlineCtx);
    }
    
    /**
     * 다건 조회 쿼리 실행 <br>
     * Execute query for multi-records.
     * 
     * @param stmtName
     *            쿼리아이디<br>
     *            query ID.
     * @param param
     *            파라미터<br>
     *            query parameter.
     * @param optionString
     *            데이타베이스 옵션<br>
     *            Options for choosing database
     * @param recordResolver
     *            레코드 가공처리<br>
     *            record resolver
     * @param onlineCtx
     *            온라인컨텍스트<br>
     *            OnlineContext object.
     * @return 조회결과<br>
     *         Result recordset.
     */
    private final IRecordSet dbSelect(String stmtName, Object param, String optionString,
                                      IHpcRecordResolver recordResolver, IOnlineContext onlineCtx) {
        OnlineContextHelper.checkTransactionValidation(onlineCtx);

        IRecordSet result = null;
        try {
            _prepareOnlineContext(onlineCtx);
            stmtName = _resolveStatementName(onlineCtx, stmtName);
            _prepareSqlLog(onlineCtx, stmtName); // contextedSqlLog 초기화 추가

            NcRowResolvedHandler handler = new NcRowResolvedHandler(stmtName, recordResolver);
            _getSqlManager(onlineCtx, optionString).queryWithRowHandler(stmtName,
                _resolveParam(onlineCtx, param),
                onlineCtx,
                handler);
            result = handler.getRecordSet();
        } catch (nexcore.framework.core.exception.DataException e) {
            throw e;
        } finally {
            _releaseSqlLog(onlineCtx, stmtName);
        }
        return result;
    }

    protected interface IHpcRecordResolver {

        void resolve(IRecord record);

    }

    private class NcRowResolvedHandler extends NcRowHandler implements IFinishRowHandler {

        private IHpcRecordResolver resolver;

        public NcRowResolvedHandler(String stmtName, IHpcRecordResolver resolver) {
            super(stmtName);
            this.resolver = resolver;
        }

        public void handleRow(Object valueObject) {
            super.handleRow(valueObject);
            if (resolver != null) {
                IRecordSet recordSet = getRecordSet();
                IRecord record = recordSet.getRecord(recordSet.getRecordCount() - 1);
                resolver.resolve(record);
            }
        }

        public void finish() {
            checkHeaders();
        }

        public Object getResult() {
            return super.getRecordSet();
        }

        public Class<?> getResultType() {
            return IRecordSet.class;
        }

        private void checkHeaders() {
            IRecordSet recordSet = getRecordSet();
            if (recordSet.getHeaderCount() < 1) {
                List<IRecordHeader> headers = ResultSetColumnNameHelper.getRecordSetHeaders();
                if (headers == null || headers.size() < 1) {
                    throw new RuntimeException("Header data is not set.");
                }
                recordSet.addHeaders(headers);
            }
        }
    }

    protected final String makeSqlApiString(String typeString, String optionString) {
        StringBuilder buff = new StringBuilder(50);
        buff.append(typeString).append("(");
        if (optionString != null) {
            buff.append(" OptionString=").append(optionString);
        }
        buff.append(")");
        return buff.toString();
    }

}
