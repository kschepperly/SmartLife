/**
* SmartLife Broadlink MultiRemote
*
* Copyright 2017 Keith Schepperly
*
* Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
* in compliance with the License. You may obtain a copy of the License at:
*
* http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software distributed under the License is distributed
* on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License
* for the specific language governing permissions and limitations under the License.
*
*/
preferences { 
input "code1", "text", title: "Button 1 Code", required: true
input "code2", "text", title: "Button 2 Code", requited: true
}
metadata {
definition (name: "SmartLife Broadlink MultiRemote", namespace: "kschepperly", author: "Keith Schepperly") 
{
capability "Actuator"
capability "Switch"
capability "Momentary"
capability "Sensor"
command "Push1"
command "Push2"
}
// simulator metadata
simulator {
}
// UI tile definitions
tiles (scale: 2) {
valueTile("btn_main", "device.btn_main", width: 2, height: 2) { 
state "val", label:"", defaultState: true, icon: "http://i96.photobucket.com/albums/l181/kcs317/SmartThings/Transparent.png"
}
standardTile("lbl_Soundbar", "device.lbl_Soundbar", width: 6, height: 1, decoration: "flat") { 
state "default", label:"JBL Cinema Base", icon: "http://i96.photobucket.com/albums/l181/kcs317/SmartThings/Transparent.png", backgroundColor: "#ffffff"
}
standardTile("btn_1", "device.btn_1", width: 1, height: 1, decoration: "flat", canChangeIcon: true, canChangeBackground: true) {
state "default", label: "Power", action: "Push1", icon: "http://i96.photobucket.com/albums/l181/kcs317/SmartThings/Power.png", backgroundColor: "#ffffff"
}
standardTile("btn_2", "device.btn_2", width: 1, height: 1, canChangeIcon: true, canChangeBackground: true) {
state "default", label: 'Mute', action: "Push2", icon: "st.Home.home30", backgroundColor: "#ffffff"
}
main "btn_main"
details (["lbl_Soundbar", "btn_1", "btn_2"])
}
}
def parse(String description) {
log.debug(description)
}
def Push1() {
    if ("/send?deviceMac=34ea34bb1b08&codeId=" + "${code1}"){
	   def result = new physicalgraph.device.HubAction(
		  method: "POST",
		  path: "/send?deviceMac=34ea34bb1b08&codeId=" + "${code1}",
		  headers: [HOST: "10.0.0.100:9876"],
		  body: ["entity_id":"${body_data_for_ha}"]
		  )
	   sendHubCommand(result)
	   //sendEvent(name: "switch", value: "on", isStateChange: true, display: false)
	   //sendEvent(name: "switch", value: "off", isStateChange: true, display: false)
	   sendEvent(name: "momentary", value: "pushed", isStateChange: true)
log.debug "Executing Push" 
log.debug result
}
}

def Push2() {
    if ("/send?deviceMac=34ea34bb1b08&codeId=" + "${code2}"){
	   def result = new physicalgraph.device.HubAction(
		  method: "POST",
		  path: "/send?deviceMac=34ea34bb1b08&codeId=" + "${code2}",
		  headers: [HOST: "10.0.0.100:9876"],
		  body: ["entity_id":"${body_data_for_ha}"]
		  )
	   sendHubCommand(result)
	   //sendEvent(name: "switch", value: "on", isStateChange: true, display: false)
	   //sendEvent(name: "switch", value: "off", isStateChange: true, display: false)
	   sendEvent(name: "momentary", value: "pushed", isStateChange: true)
log.debug "Executing Push" 
log.debug result
}
}

def on() {
push()
}

def off() {
push()
}