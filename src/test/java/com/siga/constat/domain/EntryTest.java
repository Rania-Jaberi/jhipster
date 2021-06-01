package com.siga.constat.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.siga.constat.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class EntryTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Entry.class);
        Entry entry1 = new Entry();
        entry1.setId(1L);
        Entry entry2 = new Entry();
        entry2.setId(entry1.getId());
        assertThat(entry1).isEqualTo(entry2);
        entry2.setId(2L);
        assertThat(entry1).isNotEqualTo(entry2);
        entry1.setId(null);
        assertThat(entry1).isNotEqualTo(entry2);
    }
}
