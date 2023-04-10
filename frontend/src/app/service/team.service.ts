import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { environment } from '../../environments/environment';
import {Team} from "../model/team";

@Injectable({providedIn: 'root'})
export class TeamService {
  httpOptions = {
    headers: new HttpHeaders({'Content-Type': 'application/json'})
  };
  private teamsUrl = environment.urlPath + '/teams';

  constructor(
    private http: HttpClient) {
  }

  getTeams(): Observable<Team[]> {
    return this.http.get<Team[]>(this.teamsUrl)
      .pipe(
        catchError(this.handleError<Team[]>('getTeams', []))
      );
  }

  getTeam(id: number): Observable<Team> {
    const url = `${this.teamsUrl}/get/${id}`;
    return this.http.get<Team>(url).pipe(
      catchError(this.handleError<Team>(`getTeam id=${id}`))
    );
  }

  updateTeam(team: Team): Observable<any> {
    const url = `${this.teamsUrl}/${team.id}`
    return this.http.put(url, team, this.httpOptions).pipe(
      catchError(this.handleError<any>('updateTeam'))
    );
  }

  addTeam(team: Team): Observable<Team> {
    return this.http.post<Team>(this.teamsUrl, team, this.httpOptions).pipe(
      catchError(this.handleError<Team>('addTeam'))
    );
  }

  removeTeam(id: number): Observable<any> {
    const url = `${this.teamsUrl}/remove/${id}`;
    return this.http.delete(url, this.httpOptions).pipe(
      catchError(this.handleError<any>(`removeTeam id=${id}`))
    );
  }

  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {

      console.error(error);

      return of(result as T);
    };
  }
}
