export default interface Producto {
  cantidad: number;
  id: number;
  nombre: string;
  precio: number;
  tipo: 'BEBIDA' | 'COMIDA' | 'POSTRE';
}