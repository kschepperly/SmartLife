/**
*  SmartLife Cam Child
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
    name: "SmartLife Cam Child",
    namespace: "kschepperly",
    author: "Keith Schepperly",
    description: "SmartLife Cam Child",
    category: "Safety & Security",
    iconUrl: "https://s3.amazonaws.com/smartapp-icons/Convenience/Cat-Convenience.png",
    iconX2Url: "https://s3.amazonaws.com/smartapp-icons/Convenience/Cat-Convenience@2x.png",
    iconX3Url: "https://s3.amazonaws.com/smartapp-icons/Convenience/Cat-Convenience@2x.png")
 
 
preferences {
    page(name: "mainPage", title: "New Camera", install: true, uninstall:true) {
        section("Camera Name") {
            label(name: "label", title: "Name This Camera", required: true, multiple: false, submitOnChange: true)
        }
        section("Add a Camera") {
        	input("CameraStreamPathList","enum", title: "Camera Stream Path", description: "Please enter your camera's streaming path", required:false, submitOnChange: true,
            options: [ //add your camera urls here
            ["rtsp://kschepperly:EmmaRose218@10.0.0.120:554/live/ch0":"Camera: Courtyard Gate"], //hikvision
            ["rtsp://kschepperly:EmmaRose218@10.0.0.121:554/live/ch0":"Camera: Emma"], //hikvision
		["rtsp://kschepperly:EmmaRose218@10.0.0.41:554/live/ch0":"Camera: Emma old"], //hikvision
		["$internalIP":"Camera: Dynamic"],
            ], displayDuringSetup: true)
        
            
            	input("CameraStreamPathCustom","string", title: "Camera Stream Path", description: "Please enter your camera's streaming path", defaultValue: settings?.CameraStreamPathList, required:false, displayDuringSetup: true)
        
            }
    }
    
}
 
def installed() {
    log.debug "Installed"
 
    initialize()
}
 
def updated() {
    log.debug "Updated"
 
    unsubscribe()
    initialize()
}
 
def initialize() {
	log.debug "CameraStreamPathList is $CameraStreamPathList"
    log.debug "CameraStreamPathCustom is $CameraStreamPathCustom"
	if(CameraStreamPathList) { state.CameraStreamPath = CameraStreamPathList }
    if(CameraStreamPathCustom) { state.CameraStreamPath = CameraStreamPathCustom }
    try {
        def DNI = (Math.abs(new Random().nextInt()) % 99999 + 1).toString()
        def cameras = getChildDevices()
        if (cameras) {
            removeChildDevices(getChildDevices())
        }
        def childDevice = addChildDevice("kschepperly", "SmartLife Camera", DNI, null, [name: app.label, label: app.label, completedSetup: true])
    } catch (e) {
    	log.error "Error creating device: ${e}"
    }
}
 
private removeChildDevices(delete) {
    delete.each {
        deleteChildDevice(it.deviceNetworkId)
    }
}
