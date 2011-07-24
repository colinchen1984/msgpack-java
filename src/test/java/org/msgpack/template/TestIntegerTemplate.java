package org.msgpack.template;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import org.junit.Test;
import org.msgpack.MessagePack;
import org.msgpack.TestSet;
import org.msgpack.packer.BufferPacker;
import org.msgpack.packer.Packer;
import org.msgpack.unpacker.BufferUnpacker;
import org.msgpack.unpacker.Unpacker;


public class TestIntegerTemplate {

    @Test
    public void testStreamPackStreamUnpack() throws Exception {
	new TestStreamPackStreamUnpack().testInteger();
    }

    @Test
    public void testStreamPackBufferUnpack() throws Exception {
	new TestStreamPackBufferUnpack().testInteger();
    }

    @Test
    public void testBufferPackBufferUnpack() throws Exception {
	new TestBufferPackBufferUnpack().testInteger();
    }

    @Test
    public void testBufferPackStreamUnpack() throws Exception {
	new TestBufferPackStreamUnpack().testInteger();
    }

    private static class TestStreamPackStreamUnpack extends TestSet {
	@Test @Override
	public void testInteger() throws Exception {
	    super.testInteger();
	}

	@Override
	public void testInteger(int v) throws Exception {
	    MessagePack msgpack = new MessagePack();
	    Template<Integer> tmpl = IntegerTemplate.instance;
	    ByteArrayOutputStream out = new ByteArrayOutputStream();
	    Packer packer = msgpack.createPacker(out);
	    tmpl.write(packer, v);
	    byte[] bytes = out.toByteArray();
	    Unpacker unpacker = msgpack.createUnpacker(new ByteArrayInputStream(bytes));
	    int ret = tmpl.read(unpacker, null);
	    assertEquals(v, ret);
	}
    }

    private static class TestStreamPackBufferUnpack extends TestSet {
	@Test @Override
	public void testInteger() throws Exception {
	    super.testInteger();
	}

	@Override
	public void testInteger(int v) throws Exception {
	    MessagePack msgpack = new MessagePack();
	    Template<Integer> tmpl = IntegerTemplate.instance;
	    ByteArrayOutputStream out = new ByteArrayOutputStream();
	    Packer packer = msgpack.createPacker(out);
	    tmpl.write(packer, v);
	    byte[] bytes = out.toByteArray();
	    BufferUnpacker unpacker = msgpack.createBufferUnpacker(bytes);
	    int ret = tmpl.read(unpacker, null);
	    assertEquals(v, ret);
	}
    }

    private static class TestBufferPackBufferUnpack extends TestSet {
	@Test @Override
	public void testInteger() throws Exception {
	    super.testInteger();
	}

	@Override
	public void testInteger(int v) throws Exception {
	    MessagePack msgpack = new MessagePack();
	    Template<Integer> tmpl = IntegerTemplate.instance;
	    BufferPacker packer = msgpack.createBufferPacker();
	    tmpl.write(packer, v);
	    byte[] bytes = packer.toByteArray();
	    BufferUnpacker unpacker = msgpack.createBufferUnpacker(bytes);
	    int ret = tmpl.read(unpacker, null);
	    assertEquals(v, ret);
	}
    }

    private static class TestBufferPackStreamUnpack extends TestSet {
	@Test @Override
	public void testInteger() throws Exception {
	    super.testInteger();
	}

	@Override
	public void testInteger(int v) throws Exception {
	    MessagePack msgpack = new MessagePack();
	    Template<Integer> tmpl = IntegerTemplate.instance;
	    BufferPacker packer = msgpack.createBufferPacker();
	    tmpl.write(packer, v);
	    byte[] bytes = packer.toByteArray();
	    Unpacker unpacker = msgpack.createUnpacker(new ByteArrayInputStream(bytes));
	    int ret = tmpl.read(unpacker, null);
	    assertEquals(v, ret);
	}
    }
}