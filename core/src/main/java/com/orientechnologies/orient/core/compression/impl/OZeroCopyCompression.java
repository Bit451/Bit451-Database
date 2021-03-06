/*
 *
 *  * Copyright 2010-2014 Orient Technologies LTD (info(at)orientechnologies.com)
 *  *
 *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  * you may not use this file except in compliance with the License.
 *  * You may obtain a copy of the License at
 *  *
 *  *      http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  * See the License for the specific language governing permissions and
 *  * limitations under the License.
 *  
 */

package com.orientechnologies.orient.core.compression.impl;

/**
 * @author Andrey Lomakin
 * @since 05.06.13
 */
public class OZeroCopyCompression extends OAbstractCompression {
  public static final String               NAME     = "zerocopy";

  public static final OZeroCopyCompression INSTANCE = new OZeroCopyCompression();

  @Override
  public byte[] compress(final byte[] content, final int offset, final int length) {
    if (offset == 0 && length == content.length)
      return content;

    byte[] result = new byte[length];
    System.arraycopy(content, offset, result, 0, length);

    return result;
  }

  @Override
  public byte[] uncompress(final byte[] content, final int offset, final int length) {
    if (offset == 0 && length == content.length)
      return content;

    byte[] result = new byte[length];
    System.arraycopy(content, offset, result, 0, length);

    return result;
  }

  @Override
  public String name() {
    return NAME;
  }
}
