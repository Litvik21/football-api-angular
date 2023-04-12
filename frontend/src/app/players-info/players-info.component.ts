import { Component, OnInit } from '@angular/core';
import { Player } from '../model/player';
import { PlayerService } from '../service/player.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-players-info',
  templateUrl: './players-info.component.html',
  styleUrls: ['./players-info.component.scss']
})
export class PlayersInfoComponent implements OnInit {
  players: Player[] = [];
  constructor(private playerService: PlayerService,
              private router: Router) {
  }

  ngOnInit() {
    this.getPlayers();
  }

  getPlayers(): void {
    this.playerService.getPlayers()
      .subscribe(players => this.players = players);
  }

  update(playerId: any) {
    this.router.navigate(['/players', playerId]);
  }

  transfer(playerId: any) {
    this.router.navigate(['/players/transfer', playerId]);
  }

  deletePlayer(playerId: any) {
    console.log(playerId)
    this.playerService.removePlayer(playerId);
  }
}
