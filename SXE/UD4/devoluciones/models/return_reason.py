from odoo import models, fields

class ReturnReason(models.Model):
    _name = 'return.reason'
    _description = 'Motivo de Devolución'

    name = fields.Char(string='Motivo', required=True)
    description = fields.Text(string='Descripción')
