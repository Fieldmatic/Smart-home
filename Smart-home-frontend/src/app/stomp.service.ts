import { Injectable } from '@angular/core';
import * as SockJS from 'sockjs-client';
import { Stomp } from '@stomp/stompjs';

@Injectable({
  providedIn: 'root',
})
export class StompService {
  connect() {
    const stompClient = Stomp.over(
      () => new SockJS('https://localhost:8081/api/ws')
    );
    stompClient.debug = () => {
      return;
    };
    return stompClient;
  }
}
