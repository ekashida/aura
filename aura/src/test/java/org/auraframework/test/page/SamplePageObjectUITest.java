/*
 * Copyright (C) 2013 salesforce.com, inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.auraframework.test.page;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import org.auraframework.test.util.AuraUITestingUtil;
import org.auraframework.test.util.AuraUITestingUtil.ActionDuringTransit;
import org.auraframework.test.util.AuraUITestingUtil.ActionTiming;
import org.auraframework.test.util.AuraUITestingUtil.StressAction;
/**
 * this is an example for testing UI with PageObject pattern.
 * for more info about the PageObject 'idea': https://code.google.com/p/selenium/wiki/PageObjects
 */
public class SamplePageObjectUITest extends PageObjectTestCase<SampleAuraPageObject> {

    public SamplePageObjectUITest(String name) throws MalformedURLException, URISyntaxException {
        super(name);
    }

    public void testButtonUi() throws Exception {
        //create the PageObject
        SampleAuraPageObject sapo = new SampleAuraPageObject(this.getName(), true, "uiExamples:buttonExample", this);
        //PageObject is in charge of load itself, clicking , typing , etc
        sapo.open();
        sapo.clickOnButton();
        String outputText = sapo.getOutputText();//page().getOutputText();

        //Test case is in charge of assertion.
        //we didn't input anything into inputText, so it's undefined.
        assertEquals("get different text!","Hi, undefined",outputText);
    }
    
    public void testProxy() throws MalformedURLException, URISyntaxException {
    	final SampleAuraPageObject sapo1 = new SampleAuraPageObject(this.getName(), true, "actionsTest:serverAction", this);
    	//we want to drop action "executeInForeground" right before it get send to server, the Page Object function that
    	//send the action is "clickOnButton"
    	String methodWeWantToIntercept = "clickOnButton";
    	String auraActionWeCare = "executeInForeground";
    	StressAction stressAction = AuraUITestingUtil.createStressAction(auraActionWeCare, ActionTiming.PRESEND, ActionDuringTransit.DROPACTION);
    	Map<String, StressAction> methodNameToStressActionMap = new HashMap<>();
    	methodNameToStressActionMap.put(methodWeWantToIntercept, stressAction);
    	AuraPageObjectInterface apoi = AuraPageObjectHandler.getAuraPageObjectInterface(sapo1, methodNameToStressActionMap);
    	//now do the test
    	apoi.open();
    	apoi.clickOnButton();
    }

}
