from odoo import models, fields, api
from odoo.exceptions import ValidationError

class Resolucion(models.Model):
    _name = 'resolucion'
    _description = 'Resolución del caso de devolución'

    descripcion = fields.Text(string='Descripción de la resolución', required=True)
    fecha_aplicacion = fields.Date(string='Fecha', default=fields.Date.today)

    devolucion_id = fields.Many2one(
        'devolucion',
        string='Devolución',
        required=True
    )

    # Campo relacionado para obtener el número de pedido desde Devolucion
    order_id = fields.Many2one(
        related='devolucion_id.order_ids',
        string='Número de Pedido',
        store=True,
        readonly=True
    )

    @api.constrains('devolucion_id')
    def _check_single_resolution(self):
        for record in self:
            # Verificar si ya existe una resolución asociada a la misma devolución
            existing_resolutions = self.search([('devolucion_id', '=', record.devolucion_id.id)])
            if len(existing_resolutions) > 1:
                raise ValidationError('Solo se puede asociar una resolución por devolución.')
