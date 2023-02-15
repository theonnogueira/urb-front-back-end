import { User } from "./User"
import { Usuario } from "./Usuario"

export class Cartao
{
  public id: number
  public numeroCartao: string
  public nm: string
  public status: string
  public tipoCartao: string
  public user: User
  public usuario: Usuario
}
