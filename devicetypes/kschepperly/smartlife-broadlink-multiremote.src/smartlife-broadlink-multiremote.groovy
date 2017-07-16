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
section("Button 1"){
input "code1", "text", title: "zBroadlink Code", required: true
}
  section("Button 2"){
    input "code2", "text", title: "xBroadlink Code", requited: true
}
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
standardTile("switch", "device.switch", width: 2, height: 2, canChangeIcon: true) {
state "off", label: 'Push', action: "momentary.push", icon: "st.Home.home30", backgroundColor: "#ff0000 ", nextState: "on"
state "on", label: 'Push', action: "momentary.push", icon: "st.Home.home30", backgroundColor: "#008000", nextState: "off"
}
/*standardTile("offButton", "device.button", width: 1, height: 1, canChangeIcon: true) {
state "default", label: 'Force Off', action: "switch.off", icon: "st.Home.home30", backgroundColor: "#ff0000"
}
standardTile("onButton", "device.button", width: 1, height: 1, canChangeIcon: true) {
state "default", label: 'Force On', action: "switch.on", icon: "st.Home.home30", backgroundColor: "#008000"
}*/
main "switch"
details "switch"
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
