package dms.ep.epbrpbase.ejb;

/**
 * <ul>
 * <li>업무 그룹명 : dms/에코폰</li>
 * <li>설 명 : <pre>[BC]중고폰_보고서관리</pre></li>
 * <li>작성일 : 2015-09-23 11:17:41</li>
 * <li>작성자 : 양재석 (jsyang)</li>
 * </ul>
 *
 * @author 양재석 (jsyang)
 */
public class EPBRPBaseBean extends nexcore.framework.coreext.pojo.biz.OnlineBizComponent {

    /**
     * @see nexcore.framework.coreext.pojo.biz.OnlineBizComponent#getFqId()
     */
    @Override
    public String getFqId() {
        return "ep.EPBRPBase";
    }

}
