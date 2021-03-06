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
input "code1", "text", title: "Code 1", required: true
input "code2", "text", title: "Code 2", required: true
input "code3", "text", title: "Code 3", required: true
input "code4", "text", title: "Code 4", required: true
input "code5", "text", title: "Code 5", required: true
input "code6", "text", title: "Code 6", required: true

}
metadata {
definition (name: "SmartLife Broadlink MultiRemote", namespace: "kschepperly", author: "Keith Schepperly") 
{
capability "Actuator"
capability "Switch"
capability "Momentary"
capability "Sensor"
command "Push"
command "Push1"
command "Push2"
command "Push3"
command "Push4"
command "Push5"
command "Push6"
command "Push7"
command "Push8"
command "Push9"
}
// simulator metadata
simulator {
}
// UI tile definitions
tiles (scale: 2) {
valueTile("btn_main", "device.btn_main", width: 2, height: 2) { 
state "val", label:"", defaultState: true, icon: "http://i96.photobucket.com/albums/l181/kcs317/SmartThings/Transparent.png"
}
standardTile("lbl_1", "null", width: 4, height: 1, decoration: "flat") { 
state "empty", label:"JBL Cinema Base", defaultState: true
}
valueTile("spc_1", "device.spacer1", width: 2, height: 1) { 
state "val", label:"", defaultState: true
}
standardTile("btn_1", "device.btn_1", width: 1, height: 1, decoration: "flat", canChangeIcon: true, canChangeBackground: true) {
state "default", label: 'Power', action: "Push1", icon: "http://i96.photobucket.com/albums/l181/kcs317/SmartThings/Power.png", backgroundColor: "Transparent"
}
standardTile("btn_2", "device.btn_2", width: 1, height: 1, decoration: "flat", canChangeIcon: true, canChangeBackground: true) {
state "default", label: 'Mute', action: "Push2", icon: "http://i96.photobucket.com/albums/l181/kcs317/SmartThings/Mute.png", backgroundColor: "Transparent"
}
standardTile("btn_3", "device.btn_3", width: 1, height: 1, decoration: "flat", canChangeIcon: true, canChangeBackground: true) {
state "default", label: 'Vol Down', action: "Push3", icon: "http://i96.photobucket.com/albums/l181/kcs317/SmartThings/Mute.png", backgroundColor: "Transparent"
}
standardTile("btn_4", "device.btn_4", width: 1, height: 1, decoration: "flat", canChangeIcon: true, canChangeBackground: true) {
state "default", label: 'Vol Up', action: "Push4", icon: "http://i96.photobucket.com/albums/l181/kcs317/SmartThings/Mute.png", backgroundColor: "Transparent"
}

standardTile("btn_5", "device.btn_5", width: 1, height: 1, decoration: "flat", canChangeIcon: true, canChangeBackground: true) {
state "default", label: 'Optical', action: "Push5", icon: "http://i96.photobucket.com/albums/l181/kcs317/SmartThings/Mute.png", backgroundColor: "Transparent"
}
standardTile("btn_6", "device.btn_6", width: 1, height: 1, decoration: "flat", canChangeIcon: true, canChangeBackground: true) {
state "default", label: 'Bluetooth', action: "Push6", icon: "http://i96.photobucket.com/albums/l181/kcs317/SmartThings/Mute.png", backgroundColor: "Transparent"
}
main"btn_main"
details (["lbl_1", "spc_1", "btn_1", "btn_2", "btn_3", "btn_4", "btn_5", "btn_6"])
}
}
def parse(String description) {
log.debug(description)
}

def Push1() {
	def code = "${code1}"
	Push(code)
}

def Push2() {
	def code = "${code2}"
	Push(code)
}
def Push3() {
	def code = "${code3}"
	Push(code)
}
def Push4() {
	def code = "${code4}"
	Push(code)
}
def Push5() {
	def code = "${code5}"
	Push(code)
}

def Push6() {
	def code = "${code6}"
	Push(code)
}

def Push(code) {
    if ("/send?deviceMac=34ea34bb1b08&codeId=" + "${code}"){
	   def result = new physicalgraph.device.HubAction(
		  method: "POST",
		  path: "/send?deviceMac=34ea34bb1b08&codeId=" + "${code}",
		  headers: [HOST: "10.0.0.100:9876"],
		  body: ["entity_id":"${body_data_for_ha}"]
		  )
	   sendHubCommand(result)
	   sendEvent(name: "momentary", value: "pushed", isStateChange: true)
log.debug "Executing Push" 
log.debug result
}
}