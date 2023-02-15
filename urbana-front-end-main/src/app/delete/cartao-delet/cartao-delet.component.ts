import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Cartao } from 'src/app/model/Cartao';
import { CartaoService } from 'src/app/service/cartao.service';

@Component({
  selector: 'app-cartao-delet',
  templateUrl: './cartao-delet.component.html',
  styleUrls: ['./cartao-delet.component.css'],
})
export class CartaoDeletComponent implements OnInit {
  cartao: Cartao = new Cartao();
  idPost: number;

  constructor(
    private router: Router,
    private route: ActivatedRoute,
    private cartaoService: CartaoService
  ) {}

  ngOnInit() {
    window.scroll(0, 0);

    this.idPost = this.route.snapshot.params['id'];
    this.findByIdCartao(this.idPost);
  }

  findByIdCartao(id: number) {
    this.cartaoService.getByIdCartao(id).subscribe((resp: Cartao) => {
      this.cartao = resp;
    })
  }

  apagar() {
    this.cartaoService.deleteCartao(this.idPost)
      alert('Cartão apagado com sucesso');
      this.router.navigate(['/inicio']);
  }
}
