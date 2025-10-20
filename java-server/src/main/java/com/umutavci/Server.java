package com.umutavci;

import message.MessageService;

import org.apache.thrift.TException;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.server.TServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocolFactory;

public class Server {
    public static class MessageServiceHandler implements MessageService.Iface {

        @Override
        public String sendMessage(String msg) throws TException {
            System.out.println("Message received from Python: " + msg);
            return "Java response: Hello, I got your message!";
        }
    }

    public static void main(String[] args) {
        try {
            MessageServiceHandler handler = new MessageServiceHandler();
            MessageService.Processor<MessageServiceHandler> processor =
                    new MessageService.Processor<>(handler);

            TServerTransport serverTransport = new TServerSocket(9090);
            TProtocolFactory protocolFactory = new TBinaryProtocol.Factory();

            TServer server = new TSimpleServer(
                    new TServer.Args(serverTransport)
                            .processor(processor)
                            .protocolFactory(protocolFactory)
            );

            System.out.println("Java Thrift server started (port 9090)...");
            server.serve();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
