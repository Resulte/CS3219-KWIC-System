package cn.edu.bupt.KWICSystem.socket.impl;

import cn.edu.bupt.KWICSystem.output.Impl.OutputImpl;
import cn.edu.bupt.KWICSystem.output.Output;
import cn.edu.bupt.KWICSystem.shift.CircularShifter;
import cn.edu.bupt.KWICSystem.shift.impl.CircularShifterImpl;
import cn.edu.bupt.KWICSystem.socket.SocketServer;
import cn.edu.bupt.KWICSystem.sort.Alphabetizer;
import cn.edu.bupt.KWICSystem.sort.impl.AlphabetizerImpl;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.List;

public class SocketServerImpl extends Thread implements SocketServer {
    private ServerSocket serverSocket;

    public SocketServerImpl(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        serverSocket.setSoTimeout(60000);
    }

    public void run()
    {
        while(true)
        {
            try
            {
                System.out.println("等待远程连接，端口号为：" + serverSocket.getLocalPort() + "...");
                Socket server = serverSocket.accept();
                System.out.println("远程主机地址：" + server.getRemoteSocketAddress());
                DataInputStream in = new DataInputStream(server.getInputStream());

                String str = in.readUTF();
                System.out.println(str);

                // circular shifter
                CircularShifter shifter = new CircularShifterImpl();

                // alphabetizer
                Alphabetizer alphabetizer = new AlphabetizerImpl();

                // line printer
                Output output = new OutputImpl();

                shifter.setup(str);

                // sort all shifts alphabetically
                alphabetizer.alpha(shifter);

                // print sorted shifts
                List<String> res = output.print(alphabetizer);

                DataOutputStream out = new DataOutputStream(server.getOutputStream());

                StringBuilder builder = new StringBuilder();
                builder.append("运行结果：\n");
                for (String s : res) {
                    builder.append(s + "\n");
                }
                builder.append("谢谢连接我：" + server.getLocalSocketAddress() + "\nGoodbye!");
                out.writeUTF(builder.toString());
                server.close();
            }catch(SocketTimeoutException s)
            {
                System.out.println("Socket timed out!");
                break;
            }catch(IOException e)
            {
                e.printStackTrace();
                break;
            }
        }
    }
}
