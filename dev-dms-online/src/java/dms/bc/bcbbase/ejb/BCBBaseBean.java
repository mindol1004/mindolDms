package dms.bc.bcbbase.ejb;

/**
 * <ul>
 * <li>업무 그룹명 : dms/업무공통</li>
 * <li>설 명 : </li>
 * <li>작성일 : 2015-12-09 16:30:45</li>
 * <li>작성자 : admin (admin)</li>
 * </ul>
 *
 * @author admin (admin)
 */
public class BCBBaseBean extends nexcore.framework.coreext.pojo.biz.OnlineBizComponent {

    /**
     * @see nexcore.framework.coreext.pojo.biz.OnlineBizComponent#getFqId()
     */
    @Override
    public String getFqId() {
        return "bc.BCBBase";
    }

}
