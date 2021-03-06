/*
 * Copyright 2010-2013 Luca Garulli (l.garulli--at--orientechnologies.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.orientechnologies.orient.server.hazelcast;

import com.orientechnologies.orient.server.distributed.ODistributedRequest;
import com.orientechnologies.orient.server.distributed.task.OAbstractRemoteTask;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * Hazelcast implementation of distributed peer.
 * 
 * @author Luca Garulli (l.garulli--at--orientechnologies.com)
 * 
 */
public class OHazelcastDistributedRequest implements ODistributedRequest, Externalizable {
  private long                id;
  private EXECUTION_MODE      executionMode;
  private String              senderNodeName;
  private String              databaseName;
  private long                senderThreadId;
  private OAbstractRemoteTask task;
  private String              userName = "";

  /**
   * Constructor used by serializer.
   */
  public OHazelcastDistributedRequest() {
  }

  public OHazelcastDistributedRequest(final String senderNodeName, final String databaseName, final OAbstractRemoteTask payload,
      EXECUTION_MODE iExecutionMode) {
    this.senderNodeName = senderNodeName;
    this.databaseName = databaseName;
    this.senderThreadId = Thread.currentThread().getId();
    this.task = payload;
    this.executionMode = iExecutionMode;
    id = -1;
  }

  public long getId() {
    return id;
  }

  public void setId(final long iReqId) {
    id = iReqId;
  }

  @Override
  public String getDatabaseName() {
    return databaseName;
  }

  @Override
  public OHazelcastDistributedRequest setDatabaseName(final String databaseName) {
    this.databaseName = databaseName;
    return this;
  }

  @Override
  public OAbstractRemoteTask getTask() {
    return task;
  }

  @Override
  public OHazelcastDistributedRequest setTask(final OAbstractRemoteTask payload) {
    this.task = payload;
    return this;
  }

  public String getSenderNodeName() {
    return senderNodeName;
  }

  public OHazelcastDistributedRequest setSenderNodeName(final String senderNodeName) {
    this.senderNodeName = senderNodeName;
    return this;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(final String userName) {
    this.userName = userName;
  }

  @Override
  public EXECUTION_MODE getExecutionMode() {
    return executionMode;
  }

  public OHazelcastDistributedRequest setExecutionMode(final EXECUTION_MODE executionMode) {
    this.executionMode = executionMode;
    return this;
  }

  @Override
  public void writeExternal(final ObjectOutput out) throws IOException {
    out.writeLong(id);
    out.writeUTF(senderNodeName);
    out.writeLong(senderThreadId);
    out.writeUTF(databaseName);
    out.writeObject(task);
    out.writeUTF(userName);
  }

  @Override
  public void readExternal(final ObjectInput in) throws IOException, ClassNotFoundException {
    id = in.readLong();
    senderNodeName = in.readUTF();
    senderThreadId = in.readLong();
    databaseName = in.readUTF();
    task = (OAbstractRemoteTask) in.readObject();
    userName = in.readUTF();
  }

  @Override
  public String toString() {
    final StringBuilder buffer = new StringBuilder();
    buffer.append("id=");
    buffer.append(id);
    buffer.append(" from=");
    buffer.append(senderNodeName);
    if (task != null) {
      buffer.append(" task=");
      buffer.append(task.toString());
    }
    if (userName != null) {
      buffer.append(" userName=");
      buffer.append(userName);
    }
    return buffer.toString();
  }
}
