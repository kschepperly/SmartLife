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
}
// simulator metadata
simulator {
}
// UI tile definitions
tiles {
standardTile("but1", "device.button", width: 1, height: 1, canChangeIcon: true) {
state "default", label: 'Push', action: "momentary.push", icon: "st.Home.home30", backgroundColor: "#ff0000"
}
standardTile("but2", "device.button", width: 1, height: 1, canChangeIcon: true) {
state "default", label: 'Push', action: "momentary.push", icon: "st.Home.home30", backgroundColor: "#ff0000"
}
main "but1"
details (["but1", "but2"])
}
}
def parse(String description) {
log.debug(description)
}
def push() {
if ("/send?deviceMac=34ea34bb1b08&codeId=" + "${broadlink_code}"){
def result = new physicalgraph.device.HubAction(
method: "POST",
path: "/send?deviceMac=34ea34bb1b08&codeId=" + "${broadlink_code}",
headers: [HOST: "10.0.0.100:9876"],
body: ["entity_id":"${body_data_for_ha}"]
)
sendHubCommand(result)
sendEvent(name: "switch", value: "on", isStateChange: true, display: false)
sendEvent(name: "switch", value: "off", isStateChange: true, display: false)
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
