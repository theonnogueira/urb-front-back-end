import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Cartao } from 'src/app/model/Cartao';
import { Usuario } from 'src/app/model/Usuario';
import { CartaoService } from 'src/app/service/cartao.service';
import { UsuarioService } from 'src/app/service/usuario.service';

@Component({
  selector: 'app-cartao-edit',
  templateUrl: './cartao-edit.component.html',
  styleUrls: ['./cartao-edit.component.css'],
})
export class CartaoEditComponent implements OnInit {
  cartao: Cartao = new Cartao()
  usuario: Usuario = new Usuario()
  listaUsuarios: Usuario[]
  idUsuario: number

  constructor(
    private router: Router,
    private route: ActivatedRoute,
    private cartaoService: CartaoService,
    private usuarioService: UsuarioService
  ) {}

  ngOnInit() {
    window.scroll(0,0)
    {
    }

    let id = this.route.snapshot.params['id']
    this.findByIdCartao(id)
    this.findAllusuarios()
  }

  findByIdCartao(id: number) {
      this.cartaoService.getByIdCartao(id).subscribe((resp: Cartao)=>{
      this.cartao = resp
    })
  }

  findByIdUsuario()
  {

    this.usuarioService.getByIdUsuario(this.idUsuario).subscribe((resp: Usuario)=>{
      this.usuario = resp
    })
  }

  findAllusuarios()
  {
    this.usuarioService.getAllUsuario().subscribe((resp: Usuario[])=>{
      this.listaUsuarios = resp
    })
  }

  atualizar()
  {
      this.usuario.id = this.idUsuario
      this.cartao.usuario = this.usuario

      this.cartaoService.putCartao(this.cartao).subscribe((resp: Cartao)=>{
        this.cartao = resp
        alert('Cartão atualizado com sucesso')
        this.router.navigate(['/inicio'])
      })
  }
}
