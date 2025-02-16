{
    'name': 'Devoluciones',
    'version': '1.0.0',
    'category': 'Sales',
    'summary': 'Gestión de devoluciones de los clientes',
    'description': 'Módulo para gestionar las devoluciones de los clientes.',
    'author': 'Miguel del Rio Martinez',
    'depends': ['base', 'sale'],
    'data': [
        'views/devolucion_views.xml',
        'views/resolucion_views.xml',
        'security/ir.model.access.csv',
    ],
    'installable': True,
    'application' : True,
}