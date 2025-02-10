{
    'name': 'Gestión de Devoluciones para Tienda de Ropa',
    'version': '1.0.4',
    'summary': 'Permite gestionar devoluciones de productos en la tienda de ropa',
    'author': 'Miguel del Rio',
    'category': 'Sales',
    'depends': ['sale', 'stock'],
    'data': [
        'security/ir.model.access.csv',
        'views/return_order_views.xml',
        'views/return_reason_views.xml',
        'data/return_reason_data.xml',
        'security/security.xml'
    ],
    'installable': True,
    'application': True,
}