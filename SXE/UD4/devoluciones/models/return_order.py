from odoo import models, fields, api, exceptions
from datetime import timedelta

class ReturnOrder(models.Model):
    _name = 'return.order'
    _description = 'Orden de Devolución'
    _order = 'create_date desc'

    name = fields.Char(string='Referencia', required=True, copy=False, readonly=True, default='New')
    partner_id = fields.Many2one('res.partner', string='Cliente', required=True)
    sale_order_id = fields.Many2one('sale.order', string='Pedido Relacionado', required=True)
    sale_order_line_id = fields.Many2one('sale.order.line', string='Producto Devuelto', required=True)
    reason_id = fields.Many2one('return.reason', string='Motivo de Devolución', required=True)
    state = fields.Selection([
        ('pending', 'Pendiente'),
        ('approved', 'Aprobada'),
        ('rejected', 'Rechazada'),
        ('processed', 'Procesada')
    ], string='Estado', default='pending', required=True)
    note = fields.Text(string='Notas del Cliente')

    @api.constrains('sale_order_id')
    def _check_return_deadline(self):
        """ Verifica que la devolución se realice dentro de los 30 días posteriores a la compra """
        for record in self:
            if record.sale_order_id.date_order:
                max_return_date = record.sale_order_id.date_order + timedelta(days=30)
                if fields.Date.today() > max_return_date:
                    raise exceptions.ValidationError("No se pueden realizar devoluciones después de 30 días de la compra.")

    @api.constrains('sale_order_line_id')
    def _check_product_in_order(self):
        """ Asegura que el producto a devolver pertenezca al pedido original """
        for record in self:
            if record.sale_order_line_id.order_id != record.sale_order_id:
                raise exceptions.ValidationError("El producto seleccionado no pertenece al pedido asociado.")

    def action_approve(self):
        """ Aprobar devolución (solo si no está rechazada) """
        for record in self:
            if record.state == 'rejected':
                raise exceptions.UserError("No se puede aprobar una devolución que ha sido rechazada.")
            record.state = 'approved'