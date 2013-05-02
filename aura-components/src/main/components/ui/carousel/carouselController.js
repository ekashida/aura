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
({
    onInit: function(cmp, event, helper) {    	 
    	helper.init(cmp);
    },       
    
    /**
     * Handle scrollEnd event coming from scroller
     */
    onScrollEnd : function(cmp, evt, helper){  	
    	helper.handleScrollEnd(cmp, evt);
    },
    
    /**
     * Handle scroller refreshed event
     */
    onScrollerRefreshed: function(cmp, evt, helper) {
    	if (!cmp._isSelectDefaultPageFired) {    		
    		var e = cmp.getEvent("selectDefaultPage");
    		cmp._isSelectDefaultPageFired = true;
    		
    		e.fire();
    	}
    },
    
    /**
     * Handle window resize event
     */      
	refresh: function(cmp, evt, helper) {	
        helper.refresh(cmp, evt);
    },
    
    /**
     * Handle clicking event from page indicator
     */
    pagerClicked: function (cmp, evt, helper) {    	
        var pageIndex = evt.getParam("pageIndex");
        
        helper.selectPage(cmp, pageIndex);        
        if (evt.preventDefault) evt.preventDefault();
    },

    /**
     * Handle key event from page indicator
     */
    pagerKeyed: function (cmp, evt, helper) {	
        helper.handlePagerKeyed(cmp, evt);
    },
    
    /**
     *  Handle pageSelected event     
     */    
    pageSelected: function(cmp, evt, helper) {    	
    	helper.selectPage(cmp, evt.getParam("pageIndex"));
    },
    
    selectDefaultPage: function (cmp, evt, helper) {    	
        var pageCmps = helper.getPageComponents(cmp),
        	defaultPage = cmp.get('v.defaultPage'),
        	pageToSelect = 0;
     
        if (defaultPage) {
        	pageToSelect = defaultPage;
        } else {        
	        for (var i = 0; i < pageCmps.length; i++) {
	            if (pageCmps[i].isDefault) {
	            	pageToSelect = i;
	            }
	        }
        }
        helper.selectPage(cmp, pageToSelect, 0);        
    }
})
