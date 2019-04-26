package Util;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.dictionary.CustomDictionary;
import com.hankcs.hanlp.seg.Segment;
import com.hankcs.hanlp.seg.common.Term;
import org.junit.jupiter.api.Test;

import java.util.List;

public class HanLPTest {

    @Test
    public void TestA(){
        String lineStr = "人工智能专家";
        try{
            Segment segment = HanLP.newSegment();
            segment.enableCustomDictionary(true);
            /**
             * 自定义分词+词性
             */
            CustomDictionary.add("好热","ng 0");
            List<Term> seg = segment.seg(lineStr);
            for (Term term : seg) {
                System.out.println(term.toString());
            }
        }catch(Exception ex){
            System.out.println(ex.getClass()+","+ex.getMessage());
        }
    }
}