import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { environment } from '../../environments/environment';
import {Player} from "../model/player";

@Injectable({providedIn: 'root'})
export class PlayerService {
  httpOptions = {
    headers: new HttpHeaders({'Content-Type': 'application/json'})
  };
  private playersUrl = environment.urlPath + '/players';

  constructor(
    private http: HttpClient) {
  }

  getPlayers(): Observable<Player[]> {
    return this.http.get<Player[]>(this.playersUrl)
      .pipe(
        catchError(this.handleError<Player[]>('getPlayers', []))
      );
  }

  getPlayer(id: number): Observable<Player> {
    const url = `${this.playersUrl}/get/${id}`;
    return this.http.get<Player>(url).pipe(
      catchError(this.handleError<Player>(`getPlayer id=${id}`))
    );
  }

  updatePlayer(player: Player): Observable<any> {
    const url = `${this.playersUrl}/remove/${player.id}`
    return this.http.put(url, player, this.httpOptions).pipe(
      catchError(this.handleError<any>('updatePlayer'))
    );
  }

  transferPlayer(id: number, newTeamId: number): Observable<any> {
    const url = `${this.playersUrl}/transfer/${id}?teamId=${newTeamId}`;
    return this.http.put(url, {}, this.httpOptions).pipe(
      catchError(this.handleError<any>('transferPlayer'))
    );
  }

  addPlayer(player: Player): Observable<Player> {
    return this.http.post<Player>(this.playersUrl, player, this.httpOptions).pipe(
      catchError(this.handleError<Player>('addPlayer'))
    );
  }

  removePlayer(id: number): Observable<any> {
    const url = `${this.playersUrl}/remove/${id}`;
    console.log(url);
    return this.http.delete(url, this.httpOptions).pipe(
      catchError(this.handleError<any>(`removePlayer id=${id}`))
    );
  }

  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {

      console.error(error);

      return of(result as T);
    };
  }
}
