/*******************************************************************************
 * This file is part of OpenNMS(R).
 *
 * Copyright (C) 2016-2016 The OpenNMS Group, Inc.
 * OpenNMS(R) is Copyright (C) 1999-2016 The OpenNMS Group, Inc.
 *
 * OpenNMS(R) is a registered trademark of The OpenNMS Group, Inc.
 *
 * OpenNMS(R) is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published
 * by the Free Software Foundation, either version 3 of the License,
 * or (at your option) any later version.
 *
 * OpenNMS(R) is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with OpenNMS(R).  If not, see:
 *      http://www.gnu.org/licenses/
 *
 * For more information contact:
 *     OpenNMS(R) Licensing <license@opennms.org>
 *     http://www.opennms.org/
 *     http://www.opennms.com/
 *******************************************************************************/

package org.opennms.netmgt.syslogd;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.nio.ByteBuffer;

import org.junit.Test;

public class ByteBufferXmlAdapterTest {

    private final ByteBufferXmlAdapter adapter = new ByteBufferXmlAdapter();

    /**
     * Verifies that a {@link ByteBuffer} can be successfully marshalled
     * when {@link ByteBuffer#hasArray()} is <code>true</code>.
     */
    @Test
    public void marshalByteBufferWithArray() {
        ByteBuffer bbWithArray = ByteBuffer.allocate(1);
        bbWithArray.put((byte)42);
        assertTrue("bytebuffer array should be accessbile", bbWithArray.hasArray());

        byte bytes[] = adapter.marshal(bbWithArray);
        assertArrayEquals(new byte[]{42}, bytes);
    }

    /**
     * Verifies that a {@link ByteBuffer} can be successfully marshalled
     * when {@link ByteBuffer#hasArray()} is <code>false</code>.
     */
    @Test
    public void marshalByteBufferWithoutArray() {
        ByteBuffer bbWithArray = ByteBuffer.allocate(1);
        bbWithArray.put((byte)42);

        assertTrue("bytebuffer array should be accessbile", bbWithArray.hasArray());
        ByteBuffer bbWithoutArray = bbWithArray.asReadOnlyBuffer();
        assertFalse("bytebuffer array should not be accessbile", bbWithoutArray.hasArray());

        byte bytes[] = adapter.marshal(bbWithoutArray);
        assertArrayEquals(new byte[]{42}, bytes);
    }
}
