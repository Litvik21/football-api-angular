import { Component, OnInit } from '@angular/core';
import {Player} from "../model/player";
import {FormBuilder, FormGroup} from "@angular/forms";
import {Team} from "../model/team";
import {PlayerService} from "../service/player.service";
import {TeamService} from "../service/team.service";

@Component({
  selector: 'app-player',
  templateUrl: './player.component.html',
  styleUrls: ['./player.component.scss']
})
export class PlayerComponent implements OnInit {
  players: Player[] = [];
  teamForm!: FormGroup;
  newTeam!: Team;
  teams: Team[] = [];
  firstName = '';
  lastName = '';
  birthDate = new Date;
  startCareer = new Date();

  constructor(private playerService: PlayerService,
              private teamService: TeamService,
              private fb: FormBuilder) { }

  ngOnInit() {
    this.getPlayers();
    this.getTeams();
    this.teamForm = this.fb.group({
      team: [null]
    });
  }

  getPlayers(): void {
    this.playerService.getPlayers()
      .subscribe(players => this.players = players);
  }
  getTeams(): void {
    this.teamService.getTeams()
      .subscribe(teams => this.teams = teams);
  }

  submitTeam() {
    if (this.teamForm.valid) {
      const teamId = this.teamForm.get('team')!.value;
      if (teamId !== null) {
        this.newTeam = this.teams.find(t => t.id === teamId)!;
      }
    }
  }

  add(): void {
    let id = Math.max.apply(Math, this.players.map(function (p) {return p.id;}));

    this.playerService.addPlayer({id: id + 1, firstName: this.firstName, lastName: this.lastName,
    birthDate: this.birthDate, startCareer: this.startCareer, team: this.newTeam} as Player)
      .subscribe(player => {this.players.push(player)});

    this.firstName = "";
    this.lastName = "";
    this.teamForm.reset();
  }

}
