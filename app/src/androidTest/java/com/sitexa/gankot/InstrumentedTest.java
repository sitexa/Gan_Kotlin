package com.sitexa.gankot;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.sitexa.gankot.net.SweetApi;
import com.sitexa.gankot.repository.Sweet;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by open on 17/06/2017.
 */

@RunWith(AndroidJUnit4.class)
public class InstrumentedTest {

    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();
        assertEquals("com.sitexa.gankot", appContext.getPackageName());
    }

    @Test
    public void topSweetTest() {
        SweetApi api = SweetApi.Factory.create();
        List<Sweet> sweets = api.topSweet(10, 1).blockingFirst();
        assertEquals(1,sweets.size());
    }

    @Test
    public void latestSweetTest() {
        SweetApi api = SweetApi.Factory.create();
        List<Sweet> sweets = api.latestSweet(10, 1).blockingFirst();
        assertEquals(2,sweets.size());
    }

}