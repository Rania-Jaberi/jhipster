package com.siga.constat.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.siga.constat.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class TestFaroukTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TestFarouk.class);
        TestFarouk testFarouk1 = new TestFarouk();
        testFarouk1.setId(1L);
        TestFarouk testFarouk2 = new TestFarouk();
        testFarouk2.setId(testFarouk1.getId());
        assertThat(testFarouk1).isEqualTo(testFarouk2);
        testFarouk2.setId(2L);
        assertThat(testFarouk1).isNotEqualTo(testFarouk2);
        testFarouk1.setId(null);
        assertThat(testFarouk1).isNotEqualTo(testFarouk2);
    }
}
