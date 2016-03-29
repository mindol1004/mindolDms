package dms.sc.scbbase.ejb;

/**
 * <ul>
 * <li>업무 그룹명 : dms/시스템공통</li>
 * <li>설 명 : </li>
 * <li>작성일 : 2015-06-22 16:59:52</li>
 * <li>작성자 : admin (admin)</li>
 * </ul>
 *
 * @author admin (admin)
 */
public class SCBBaseBean extends nexcore.framework.coreext.pojo.biz.OnlineBizComponent {

    /**
     * @see nexcore.framework.coreext.pojo.biz.OnlineBizComponent#getFqId()
     */
    @Override
    public String getFqId() {
        return "sc.SCBBase";
    }

}
