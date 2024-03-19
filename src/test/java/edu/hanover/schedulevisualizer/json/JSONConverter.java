package edu.hanover.schedulevisualizer.json;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.json.JsonReadFeature;
import edu.hanover.schedulevisualizer.core.entity.Course;
import edu.hanover.schedulevisualizer.core.simpleEntity.SimpleCourse;
import edu.hanover.schedulevisualizer.core.simpleEntity.SimpleEntityFactory;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class JSONConverter {
    JsonFactory factory = JsonFactory.builder()
// configure, if necessary:
            .enable(JsonReadFeature.ALLOW_JAVA_COMMENTS)
            .build();

    void toJSON(Writer w, Course c) {
        try {
            JsonGenerator generator = factory.createGenerator(w);
            generator.writeStartObject(c);
            generator.writeStringField("prefix", c.getPrefix());
            generator.writeStringField("courseNum", c.getCourseNum());
            generator.writeStringField("courseName", c.getCourseName());
            generator.writeEndObject();
            generator.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Disabled
    @Test
    void testWrite() {
        StringWriter w = new StringWriter();
        Course c = new SimpleEntityFactory().makeCourse("CS", "220", "Fundamentals");
        toJSON(w, c);
        assertThat(w.toString(), equalTo(""));
    }
}
