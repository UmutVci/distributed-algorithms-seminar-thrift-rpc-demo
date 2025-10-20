import sys
import os

# Docker içi path
sys.path.append(os.path.join(os.path.dirname(__file__), 'gen-py'))

from message import MessageService
from thrift.transport import TSocket, TTransport
from thrift.protocol import TBinaryProtocol
from thrift.Thrift import TException


def main():
    transport = TSocket.TSocket('java-server', 9090)
    transport = TTransport.TBufferedTransport(transport)
    protocol = TBinaryProtocol.TBinaryProtocol(transport)
    client = MessageService.Client(protocol)

    try:
        transport.open()
        print("Conntected to Java Server")

        msg = "Hi Java! Here is Python!"
        response = client.sendMessage(msg)
        print("Message from Java", response)
    except TException as e:
        print("Conntection Error ; ", e)
    finally:
        transport.close()


if __name__ == "__main__":
    main()
