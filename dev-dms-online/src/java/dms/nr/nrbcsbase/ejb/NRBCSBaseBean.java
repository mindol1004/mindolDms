package dms.nr.nrbcsbase.ejb;

/**
 * <ul>
 * <li>업무 그룹명 : dms/신규R</li>
 * <li>설 명 : </li>
 * <li>작성일 : 2015-08-06 10:40:15</li>
 * <li>작성자 : 박홍서 (uni9401)</li>
 * </ul>
 *
 * @author 박홍서 (uni9401)
 */
public class NRBCSBaseBean extends nexcore.framework.coreext.pojo.biz.OnlineBizComponent {

    /**
     * @see nexcore.framework.coreext.pojo.biz.OnlineBizComponent#getFqId()
     */
    @Override
    public String getFqId() {
        return "nr.NRBCSBase";
    }

}
