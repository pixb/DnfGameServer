package com.dnfm.mina.udp;

public interface PacketId {
   int PacketStart = 1000;
   int PacketAck = 1001;
   int InitRequest = 1002;
   int InitResponse = 1003;
   int LoginRequest = 1004;
   int LoginResponse = 1005;
   int LogoutRequest = 1006;
   int LogoutResponse = 1007;
   int ReadyRequest = 1008;
   int ReadyResponse = 1009;
   int InputRequest = 1010;
   int FrameNoti = 1011;
   int EmptyInputRequest = 1012;
   int Ping = 1013;
   int Pong = 1014;
   int EmptyFrameRequest = 1015;
   int Start = 1016;
   int ReStart = 1017;
   int CheckStartRequest = 1018;
   int CheckStartResponse = 1019;
   int PacketEnd = 1020;
}
