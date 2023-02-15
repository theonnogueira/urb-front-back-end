import { environment } from './../../environments/environment.development';
import { Router } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import { Cartao } from '../model/Cartao';
import { CartaoService } from '../service/cartao.service';
import { UsuarioService } from '../service/usuario.service';
import { Usuario } from '../model/Usuario';
import { User } from '../model/User';

@Component({
  selector: 'app-inicio',
  templateUrl: './inicio.component.html',
  styleUrls: ['./inicio.component.css'],
})
export class InicioComponent implements OnInit {
  cartao: Cartao = new Cartao();
  listaCartao: Cartao[];

  usuario: Usuario = new Usuario();
  listaUsuarios: Usuario[];
  idUsuario: number;
  idUser: number;
  user: User = new User();

  constructor(
    private router: Router,
    private cartaoService: CartaoService,
    private usuarioService: UsuarioService
  ) {}

  ngOnInit() {
    if (environment.token == '') {
      //alert('Usuário deslogado, faça o login novamente')
      this.router.navigate(['/entrar']);
    }

    this.getAllUsuarios();
    this.getAllCartoes();
  }

  getAllUsuarios() {
    this.usuarioService.getAllUsuario().subscribe((resp: Usuario[]) => {
      this.listaUsuarios = resp;
    });
  }

  findByIdUsuario() {
    this.usuarioService
      .getByIdUsuario(this.idUsuario)
      .subscribe((resp: Usuario) => {
        this.usuario = resp;
      });
  }

  getAllCartoes() {
    this.cartaoService.getAllCartoes().subscribe((resp: Cartao[]) => {
      this.listaCartao = resp;
    });
  }

  publicar() {
    this.usuario.id = this.idUsuario;
    this.cartao.usuario = this.usuario;

    this.user.id = this.idUser;
    this.cartao.user = this.user;

    this.cartaoService.postCartao(this.cartao).subscribe((resp: Cartao) => {
      this.cartao = resp;
      alert('Cartão cadastrado com sucesso');
      this.cartao = new Cartao();
      this.getAllCartoes();
    });
  }
}
