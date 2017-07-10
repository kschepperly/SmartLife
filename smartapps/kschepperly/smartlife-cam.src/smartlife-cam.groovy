/**
* SmartLife Camera
*
*  Copyright 2017 Keith Schepperly
*
*  Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
*  in compliance with the License. You may obtain a copy of the License at:
*
*      http://www.apache.org/licenses/LICENSE-2.0
*
*  Unless required by applicable law or agreed to in writing, software distributed under the License is distributed
*  on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License
*  for the specific language governing permissions and limitations under the License.
*
*/
definition(
    name: "SmartLife Cam",
    namespace: "kschepperly",
    author: "Keith Schepperly",
    description: "This smartapp installs the SmartLife Cam App so you can add multiple child video cameras",
    category: "Safety & Security",
    iconUrl: "https://s3.amazonaws.com/smartapp-icons/Convenience/Cat-Convenience.png",
    iconX2Url: "https://s3.amazonaws.com/smartapp-icons/Convenience/Cat-Convenience@2x.png",
    iconX3Url: "https://s3.amazonaws.com/smartapp-icons/Convenience/Cat-Convenience@2x.png",
    singleInstance: true)
 {
     appSetting "internalIP"
     appSetting "externalIP"
 }
 
preferences {
    page(name: "mainPage", title: "Camera Setup", install: true, uninstall: true){
		section("Existing")
	{
        if(state?.installed) {
            section("Add a New Camera") {
                app "SmartLife Cam Child", "kschepperly", "SmartLife Cam Child", title: "New Camera", page: "mainPage", multiple: true, install: true
            }
        } else {
            section("Initial Install") {
                paragraph "This smartapp installs the SmartLife Cam App so you can add multiple child video cameras. Click install / done then go to smartapps in the flyout menu and add new cameras or edit existing cameras."
            }
        }
    }
}
}
 
def installed() {
    log.debug "Installed with settings: ${settings}"
 
    initialize()
}
 
def updated() {
    log.debug "Updated with settings: ${settings}"
 
    unsubscribe()
    initialize()
}
 
def initialize() {
    state.installed = true
} 
