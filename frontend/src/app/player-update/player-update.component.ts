import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Player } from '../model/player';
import { Team } from '../model/team';
import { PlayerService } from '../service/player.service';
import { ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';
import { TeamService } from '../service/team.service';

@Component({
  selector: 'app-player-update',
  templateUrl: './player-update.component.html',
  styleUrls: ['./player-update.component.scss']
})
export class PlayerUpdateComponent implements OnInit {
  teamForm!: FormGroup;
  player: any;
  players: Player[] = [];
  teams: Team[] = [];
  newTeam!: Team;
  firstName = '';
  lastName = '';
  birthDate!: Date;
  startCareer!: Date;

  constructor(private fb: FormBuilder,
              private playerService: PlayerService,
              private route: ActivatedRoute,
              private location: Location,
              private teamService: TeamService) {
  }

  ngOnInit(): void {
    this.getPlayer();
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
      const teamID = this.teamForm.get('team')!.value;
      if (teamID !== null) {
        this.newTeam = this.teams.find(t => t.id === teamID)!;
      }
    }
  }

  getPlayer(): void {
    const id = +this.route.snapshot.paramMap.get('id')!;
    this.playerService.getPlayer(id)
      .subscribe(player => {
        this.player = player;
        this.firstName = player.firstName;
        this.lastName = player.lastName;
        this.teamForm = this.fb.group({
          team: [this.player.teamId]
        });
      });
  }

  updatePlayer(): void {
    this.submitTeam();
    this.player = {
      id: this.player.id,
      firstName: this.firstName != '' ? this.firstName : this.player.firstName,
      lastName: this.lastName != '' ? this.lastName : this.player.lastName,
      birthDate: this.birthDate ? this.birthDate : this.player.birthDate,
      startCareer: this.startCareer ? this.startCareer : this.player.startCareer,
      teamId: this.newTeam?.id ?? this.player.teamId
    };

    this.playerService.updatePlayer(this.player)
      .subscribe(() => this.goBack());
  }

  goBack(): void {
    this.location.back();
  }
}
