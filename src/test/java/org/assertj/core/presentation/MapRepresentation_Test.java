package org.assertj.core.presentation;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Vojislav Marinkovic
 */
public class MapRepresentation_Test {
    @Test
    public void should_format_map_according_to_normal_representation() {
        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put("a", 1);
        map.put("b", 2);
        map.put("c", 3);

        Representation mapRepresentation = new MapRepresentation();
        String output = mapRepresentation.toStringOf(map);
        assertThat(output).isNotNull().startsWith("{").endsWith("}");
        assertThat(output.split(",").length).isEqualTo(3);

        for(Map.Entry mapEntry : map.entrySet()){
            assertThat(output).contains(String.format("\"%s\"=%d", mapEntry.getKey(), mapEntry.getValue()));
        }
    }

    @Test
    public void should_format_single_entry_map_according_to_normal_representation() {
        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put("a", 1);

        Representation mapRepresentation = new MapRepresentation();
        String output = mapRepresentation.toStringOf(map);
        assertThat(output).isNotNull();
        assertThat(output).isEqualTo(String.format("{\"%s\"=%d}", "a", 1));
    }

    @Test
    public void should_format_map_according_to_key_diff_representation() {
        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put("a", 1);
        map.put("b", 2);
        map.put("c", 3);

        Representation mapRepresentation = new MapRepresentation();
        ((MapRepresentation) mapRepresentation).addUnequalEntryByKey("b");
        String output = mapRepresentation.toStringOf(map);
        assertThat(output).isNotNull().startsWith("{").endsWith("}");
        assertThat(output.split(",").length).isEqualTo(3);

        for(Map.Entry mapEntry : map.entrySet()){
            if (mapEntry.getKey().equals("b")){
                assertThat(output).contains(String.format("[\"%s\"=%d]", mapEntry.getKey(), mapEntry.getValue()));
            }
            else {
                assertThat(output).contains(String.format("\"%s\"=%d", mapEntry.getKey(), mapEntry.getValue()));
            }
        }
    }

    @Test
    public void should_format_single_item_map_according_to_key_diff_representation() {
        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put("a", 1);

        Representation mapRepresentation = new MapRepresentation();
        ((MapRepresentation) mapRepresentation).addUnequalEntryByKey("a");
        String output = mapRepresentation.toStringOf(map);
        assertThat(output).isNotNull();
        assertThat(output).isEqualTo(String.format("{[\"%s\"=%d]}", "a", 1));
    }

    @Test
    public void should_format_map_according_to_value_diff_representation() {
        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put("a", 1);
        map.put("b", 2);
        map.put("c", 3);

        Representation mapRepresentation = new MapRepresentation();
        ((MapRepresentation) mapRepresentation).addUnequalEntryByValue("b");
        String output = mapRepresentation.toStringOf(map);
        assertThat(output).isNotNull().startsWith("{").endsWith("}");
        assertThat(output.split(",").length).isEqualTo(3);

        for(Map.Entry mapEntry : map.entrySet()){
            if (mapEntry.getKey().equals("b")){
                assertThat(output).contains(String.format("\"%s\"=[%d]", mapEntry.getKey(), mapEntry.getValue()));
            }
            else {
                assertThat(output).contains(String.format("\"%s\"=%d", mapEntry.getKey(), mapEntry.getValue()));
            }
        }
    }

    @Test
    public void should_format_single_entry_map_according_to_value_diff_representation() {
        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put("a", 1);

        Representation mapRepresentation = new MapRepresentation();
        ((MapRepresentation) mapRepresentation).addUnequalEntryByValue("a");
        String output = mapRepresentation.toStringOf(map);
        assertThat(output).isNotNull();
        assertThat(output).isEqualTo(String.format("{\"%s\"=[%d]}", "a", 1));
    }

    @Test
    public void should_return_null() {
        Representation mapRepresentation = new MapRepresentation();
        String output = mapRepresentation.toStringOf(null);
        assertThat(output).isNull();
    }

    @Test
    public void should_return_empty_map_representation() {
        Representation mapRepresentation = new MapRepresentation();
        String output = mapRepresentation.toStringOf(new HashMap());
        assertThat(output).isEqualTo("{}");
    }
}
