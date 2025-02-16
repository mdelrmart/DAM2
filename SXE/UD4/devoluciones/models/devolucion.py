from odoo import models, fields

class Devolucion(models.Model):
    _name = 'devolucion'
    _description = 'Devolución Solicitada'
    
    order_ids = fields.Many2one(
        'sale.order',
        string='Pedido',
        required=True
    )
    descripcion = fields.Text(string='Motivo')
    fecha_solicitud = fields.Date(string='Fecha de Solicitud', default=fields.Date.today)
    estado = fields.Selection([
        ('rechazado', 'Rechazado'),
        ('procesando', 'En Proceso'),
        ('realizado', 'Realizado')
    ], string='Estado', default='procesando')
    resolucion_ids = fields.One2many('resolucion', 'devolucion_id', string='Seguimiento de la resolución')
    