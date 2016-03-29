package dms.nr.nrbeibase.ejb;

/**
 * <ul>
 * <li>업무 그룹명 : dms/신규R</li>
 * <li>설 명 : </li>
 * <li>작성일 : 2015-10-13 13:14:31</li>
 * <li>작성자 : 이영진 (newnofixing)</li>
 * </ul>
 *
 * @author 이영진 (newnofixing)
 */
public class NRBEIBaseBean extends nexcore.framework.coreext.pojo.biz.OnlineBizComponent {

    /**
     * @see nexcore.framework.coreext.pojo.biz.OnlineBizComponent#getFqId()
     */
    @Override
    public String getFqId() {
        return "nr.NRBEIBase";
    }

}
