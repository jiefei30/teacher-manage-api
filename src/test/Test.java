import com.hist.teachermanage.common.util.StringUtils;

/**
 * @author Yangluxin
 * @date 2020/7/23 18:53
 */
public class Test {
    public static void main(String[] args) {
        String md5 = StringUtils.md5Encode("123456");
        System.out.println(md5);
    }
}
