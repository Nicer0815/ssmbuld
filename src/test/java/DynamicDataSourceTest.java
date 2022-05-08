import com.ning.service.BookService;
import com.ning.service.impl.BookServiceImpl;
import com.ning.utils.DynamicDataSource;
import com.ning.utils.DynamicDataSourceHolder;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class DynamicDataSourceTest {
    @Autowired  //找了一下午的bug
    @Qualifier("BookServiceImpl")
    private BookService bookService;

    @Test
    public static void test(){
        System.out.println("datasource!!!!");
        DynamicDataSourceHolder.setDataSourceType(DynamicDataSourceHolder.DATE_SOURCE_DEVELOPER);
        //bookService.queryBookById(11111);
    }
}
