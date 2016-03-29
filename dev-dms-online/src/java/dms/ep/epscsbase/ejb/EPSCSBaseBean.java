package dms.ep.epscsbase.ejb;

/**
 * <ul>
 * <li>업무 그룹명 : dms/에코폰</li>
 * <li>설 명 : <pre>[SC]중고폰_상담관리</pre></li>
 * <li>작성일 : 2015-08-18 15:55:55</li>
 * <li>작성자 : 김상오 (myneti99)</li>
 * </ul>
 *
 * @author 김상오 (myneti99)
 */
public class EPSCSBaseBean extends nexcore.framework.coreext.pojo.biz.SharedBizComponent {

    /**
     * @see nexcore.framework.coreext.pojo.biz.SharedBizComponent#getFqId()
     */
    @Override
    public String getFqId() {
        return "ep.EPSCSBase";
    }

}
