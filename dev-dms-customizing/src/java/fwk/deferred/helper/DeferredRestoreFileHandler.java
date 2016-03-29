package fwk.deferred.helper;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.logging.Log;

import nexcore.framework.core.log.LogManager;
import nexcore.framework.core.util.DateUtils;
import nexcore.framework.deferred.DeferredStatus;
/**
 * 장애시 복원 정보를 기록하고 조회하는 클래스
 */
public class DeferredRestoreFileHandler {

	private String dirPath;
    private Log log = LogManager.getFwkLog();
	/**
	 * 은행별로 디렉터리 경로를 다르게 지정해야 한다.
	 * @param dirPath
	 */
	public void setDirPath(String dirPath) {
		this.dirPath = dirPath;
	}

	/**
	 * 장애 정보를 기록
	 * @param logBzopDt 로그 영업일
	 * @param seqNo 일련번호
	 * @param targetTxId 대상 거래코드
	 * @param resultStatus 처리 상태
	 * @param exeCnt 실행횟수
	 * @throws Exception
	 */
	public void write(String logBzopDt, long seqNo, String targetTxId, DeferredStatus resultStatus, int exeCnt) throws Exception {
		//<일련번호>_<호출거래코드>=<처리결과>:<처리횟수>:<일시>
		StringBuilder buff = new StringBuilder();
		buff.append(seqNo).append("_").append(targetTxId).append("=").append(resultStatus.getId()).append(":").append(exeCnt).append(":").append(DateUtils.getCurrentDate("yyyyMMddHHmmssSSS")).append("\n");

		File file = getFile(logBzopDt);
		if (!file.exists()) {
			File dir = file.getParentFile();
			if (!dir.exists()) {
				dir.mkdirs();
			}
		}

		BufferedWriter bw = null;
		try {
			bw = new BufferedWriter(new FileWriter(file, true));
			bw.write(buff.toString());
			bw.flush();
		} finally {
			if (bw != null) {
				try {
					bw.close();
				} catch (Exception e) {
				    //2015.10.13 jihooyim code inspector 점검 수정 (02. 오류 상황 대응 부재)
				    if (log.isErrorEnabled())  log.error("BeanUtils.cloneBean error");
				}
			}
		}
	}

	/**
	 * 로그 영업일에 해당하는 모든 정보를 조회.
	 * @param logBzopDt 로그영업일
	 * @return 복원 정보
	 */
	public Properties read(String logBzopDt) {
		File file = getFile(logBzopDt);
		if (file.exists()) {
			InputStream is = null;
			try {
				is = new FileInputStream(file);
				Properties props = new Properties();
				props.load(is);
				return props;
			} catch (Exception e) {
			    return null;
			} finally {
				if (is != null) {
					try {
						is.close();
					} catch (Exception e) {
					   //2015.10.13 jihooyim code inspector 점검 수정 (02. 오류 상황 대응 부재)
					    if (log.isErrorEnabled())  if (log.isErrorEnabled())  log.error("close error");
					}
				}
			}
		}
		return null;
	}

	/**
	 * 복원정보에 해당 거래 존재여부 확인
	 * @param props 복원정보
	 * @param seqNo 일련번호
	 * @param targetTxId 대상 거래코드
	 * @return 복원 대상 여부
	 */
	public Info contains(Properties props, long seqNo, String targetTxId) {
		if (props != null) {
			String value = props.getProperty(seqNo + "_" + targetTxId, "").trim();
			if (value.length() > 0) {
				String[] array = value.split(":");
				if (array != null && array.length == 3) {
					Info info = new Info();
					info.status = array[0];
					try {
						info.exeCnt = Integer.parseInt(array[1]);
					} catch (Exception e) {
					  //2015.10.13 jihooyim code inspector 점검 수정 (02. 오류 상황 대응 부재)
					    if (log.isErrorEnabled())  log.error("Integer.parseInt error");
					}
					info.updDate = array[2];
				}
			}
		}
		return null;
	}

	private File getFile(String logBzopDt) {
		return new File(dirPath + "/" + logBzopDt + ".dat");
	}

	public static class Info {
		public String status;
		public int exeCnt;
		public String updDate;

		public String toString() {
			return "status=" + status + ", exeCnt=" + exeCnt + ", updDate=" + updDate;
		}
	}

}
