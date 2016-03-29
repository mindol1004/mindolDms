package dms.sc.scsbase.ejb;

/**
 * <ul>
 * <li>업무 그룹명 : dms/시스템공통</li>
 * <li>설 명 : </li>
 * <li>작성일 : 2015-07-13 12:41:50</li>
 * <li>작성자 : admin (admin)</li>
 * </ul>
 *
 * @author admin (admin)
 */
public class SCSBaseBean extends nexcore.framework.coreext.pojo.biz.SharedBizComponent {

    /**
     * @see nexcore.framework.coreext.pojo.biz.SharedBizComponent#getFqId()
     */
    @Override
    public String getFqId() {
        return "sc.SCSBase";
    }

}
