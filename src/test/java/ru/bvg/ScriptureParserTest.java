package ru.bvg;

import org.junit.Test;
import ru.bvg.enumeration.ScriptureEnum;
import ru.bvg.model.Media;
import ru.bvg.util.ScriptureParser;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

public class ScriptureParserTest {

    @Test
    public void testParseBG(){
        List<Media.Scripture> scriptures = ScriptureParser.parse(ScriptureEnum.BHAGAVAD_GITA, "14.5");
        assertEquals(1, scriptures.size());
        assertNull(scriptures.get(0).getCanto());
        assertEquals(Integer.valueOf(14), scriptures.get(0).getChapter());
        assertEquals(Integer.valueOf(5), scriptures.get(0).getVerse());

        scriptures = ScriptureParser.parse(ScriptureEnum.BHAGAVAD_GITA, "1-2");
        assertEquals(2, scriptures.size());
        assertNull(scriptures.get(0).getCanto());
        assertNull(scriptures.get(1).getCanto());
        assertEquals(Integer.valueOf(1), scriptures.get(0).getChapter());
        assertEquals(Integer.valueOf(2), scriptures.get(1).getChapter());

        scriptures = ScriptureParser.parse(ScriptureEnum.BHAGAVAD_GITA, "11.04-5");
        assertEquals(2, scriptures.size());
        assertNull(scriptures.get(0).getCanto());
        assertNull(scriptures.get(1).getCanto());
        assertEquals(Integer.valueOf(11), scriptures.get(0).getChapter());
        assertEquals(Integer.valueOf(11), scriptures.get(1).getChapter());
        assertEquals(Integer.valueOf(4), scriptures.get(0).getVerse());
        assertEquals(Integer.valueOf(5), scriptures.get(1).getVerse());

        try {
            ScriptureParser.parse(ScriptureEnum.BHAGAVAD_GITA, "11-12.5");
            fail("Should be exception");
        }catch (RuntimeException e){
            assertEquals("java.lang.NumberFormatException", e.getClass().getName());
        }
    }

    @Test
    public void testParseSB() {
        List<Media.Scripture> scriptures = ScriptureParser.parse(ScriptureEnum.SRIMAD_BHAGAVATAM, "5.3.21");
        assertEquals(1, scriptures.size());
        assertEquals(Integer.valueOf(5), scriptures.get(0).getCanto());
        assertEquals(Integer.valueOf(3), scriptures.get(0).getChapter());
        assertEquals(Integer.valueOf(21), scriptures.get(0).getVerse());

        scriptures = ScriptureParser.parse(ScriptureEnum.SRIMAD_BHAGAVATAM, "5.3");
        assertEquals(1, scriptures.size());
        assertEquals(Integer.valueOf(5), scriptures.get(0).getCanto());
        assertEquals(Integer.valueOf(3), scriptures.get(0).getChapter());
        assertNull(scriptures.get(0).getVerse());

        scriptures = ScriptureParser.parse(ScriptureEnum.SRIMAD_BHAGAVATAM, "1.2.10-21");
        assertEquals(12, scriptures.size());
        assertEquals(Integer.valueOf(1), scriptures.get(0).getCanto());
        assertEquals(Integer.valueOf(1), scriptures.get(1).getCanto());
        assertEquals(Integer.valueOf(2), scriptures.get(0).getChapter());
        assertEquals(Integer.valueOf(2), scriptures.get(1).getChapter());
        assertEquals(Integer.valueOf(10), scriptures.get(0).getVerse());
        assertEquals(Integer.valueOf(11), scriptures.get(1).getVerse());
    }

    @Test
    public void testParseCC() {
        List<Media.Scripture> scriptures = ScriptureParser.parse(ScriptureEnum.CAITANYA_CHARITAMRITA, "Ади 4.132");
        assertEquals(1, scriptures.size());
        assertEquals(Integer.valueOf(1), scriptures.get(0).getCanto());
        assertEquals(Integer.valueOf(4), scriptures.get(0).getChapter());
        assertEquals(Integer.valueOf(132), scriptures.get(0).getVerse());

        scriptures = ScriptureParser.parse(ScriptureEnum.CAITANYA_CHARITAMRITA, "Ади 4.132-133");
        assertEquals(2, scriptures.size());
        assertEquals(Integer.valueOf(1), scriptures.get(0).getCanto());
        assertEquals(Integer.valueOf(1), scriptures.get(1).getCanto());
        assertEquals(Integer.valueOf(4), scriptures.get(0).getChapter());
        assertEquals(Integer.valueOf(4), scriptures.get(1).getChapter());
        assertEquals(Integer.valueOf(132), scriptures.get(0).getVerse());
        assertEquals(Integer.valueOf(133), scriptures.get(1).getVerse());
    }
}
