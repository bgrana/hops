/*
 * Copyright (C) 2015 hops.io.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.hadoop.distributedloadsimulator.sls.scheduler;

import io.hops.ha.common.TransactionState;
import org.apache.hadoop.net.Node;
import org.apache.hadoop.yarn.api.records.ApplicationId;
import org.apache.hadoop.yarn.api.records.ContainerId;
import org.apache.hadoop.yarn.api.records.NodeId;
import org.apache.hadoop.yarn.api.records.NodeState;
import org.apache.hadoop.yarn.api.records.Resource;
import org.apache.hadoop.yarn.api.records.ResourceOption;
import org.apache.hadoop.yarn.server.api.protocolrecords.NodeHeartbeatResponse;
import org.apache.hadoop.yarn.server.resourcemanager.rmnode.RMNode;
import org.apache.hadoop.yarn.server.resourcemanager.rmnode
        .UpdatedContainerInfo;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import static org.apache.hadoop.distributedloadsimulator.sls.SLSRunner.LOG;
import org.apache.hadoop.yarn.server.resourcemanager.recovery.RMStateStore;

public class RMNodeWrapper implements RMNode {
  private RMNode node;
  private List<UpdatedContainerInfo> updates;
  private boolean pulled = false;
  
  public RMNodeWrapper(RMNode node,TransactionState ts) {
    this.node = node;
    updates = node.pullContainerUpdates(ts);
  }
  
  @Override
  public NodeId getNodeID() {
    return node.getNodeID();
  }

  @Override
  public String getHostName() {
    return node.getHostName();
  }

  @Override
  public int getCommandPort() {
    return node.getCommandPort();
  }

  @Override
  public int getHttpPort() {
    return node.getHttpPort();
  }

  @Override
  public String getNodeAddress() {
    return node.getNodeAddress();
  }

  @Override
  public String getHttpAddress() {
    return node.getHttpAddress();
  }

  @Override
  public String getHealthReport() {
    return node.getHealthReport();
  }

  @Override
  public long getLastHealthReportTime() {
    return node.getLastHealthReportTime();
  }

    @Override
  public void setLastNodeHeartBeatResponseId(int id) {
    node.setLastNodeHeartBeatResponseId(id);
  }

  @Override
  public Resource getTotalCapability() {
    return node.getTotalCapability();
  }

  @Override
  public String getRackName() {
    return node.getRackName();
  }

  @Override
  public Node getNode() {
    return node.getNode();
  }

  @Override
  public NodeState getState() {
    return node.getState();
  }

  @Override
  public List<ContainerId> getContainersToCleanUp() {
    return node.getContainersToCleanUp();
  }

  @Override
  public void setContainersToCleanUp(Set<ContainerId> newSet){
    node.setContainersToCleanUp(newSet);
  }
  
  @Override
  public void setAppsToCleanup(List<ApplicationId> newList) {
    node.setAppsToCleanup(newList);
  }
  
  @Override
  public List<ApplicationId> getAppsToCleanup() {
    return node.getAppsToCleanup();
  }

  @Override
  public void setNextHeartBeat(boolean nextHeartBeat) {
    node.setNextHeartBeat(nextHeartBeat);
  }
      
  @Override
  public void updateNodeHeartbeatResponseForCleanup(
          NodeHeartbeatResponse nodeHeartbeatResponse, TransactionState ts) {
    node.updateNodeHeartbeatResponseForCleanup(nodeHeartbeatResponse, ts);
  }

  @Override
  public NodeHeartbeatResponse getLastNodeHeartBeatResponse() {
    return node.getLastNodeHeartBeatResponse();
  }

  @Override
  @SuppressWarnings("unchecked")
  public List<UpdatedContainerInfo> pullContainerUpdates(TransactionState ts) {
    List<UpdatedContainerInfo> list = Collections.EMPTY_LIST;
    if (! pulled) {
      list = updates;
      pulled = true;
    }
    return list;    
  }
  
  List<UpdatedContainerInfo> getContainerUpdates() {
    return updates;
  }

  @Override
  public String getNodeManagerVersion() {
    return node.getNodeManagerVersion();
  }

  @Override
  public void setResourceOption(ResourceOption resourceOption) {
    node.setResourceOption(resourceOption);
  }
  
  @Override
  public ResourceOption getResourceOption() {
    return node.getResourceOption();
  }

    @Override
    public void recover(RMStateStore.RMState state) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

  
}
