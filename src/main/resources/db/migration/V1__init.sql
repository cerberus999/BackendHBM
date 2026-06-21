create schema if not exists ventas;

create schema if not exists clientela;

-- =============================================================================
-- SCHEMA: VENTAS
-- =============================================================================

set search_path to ventas;

create table distribuidores (
  id_distribuidor bigint primary key generated always as identity,
  nombre varchar(100) not null,
  tipo_relacion varchar(30) not null check (
    tipo_relacion in ('intermediario', 'importacion_directa')
  ),
  pais_origen varchar(100) not null,
  contacto_email varchar(100),
  telefono varchar(20),
  activo boolean default true
);

create table motos (
  id_moto bigint primary key generated always as identity,
  id_distribuidor bigint not null references distribuidores (id_distribuidor),
  marca varchar(50) not null check (
    marca in ('Suzuki', 'Yamaha', 'Honda', 'Kawasaki')
  ),
  modelo varchar(100) not null,
  "año" int not null,
  tipo varchar(50),
  cilindrada_cc int,
  precio_importacion numeric(12, 2) not null,
  precio_venta numeric(12, 2) not null,
  stock int not null default 0,
  color varchar(50),
  imagen_url text,
  estado varchar(30) default 'disponible' check (
    estado in ('disponible', 'agotado', 'descontinuado')
  )
);

create table importaciones (
  id_importacion bigint primary key generated always as identity,
  id_moto bigint not null references motos (id_moto),
  id_distribuidor bigint not null references distribuidores (id_distribuidor),
  numero_pedido varchar(50) unique,
  fecha_pedido date not null,
  fecha_estimada_llegada date,
  fecha_llegada_real date,
  cantidad int not null,
  costo_unitario_usd numeric(12, 2),
  costo_total_usd numeric(14, 2),
  estado_envio varchar(30) check (
    estado_envio in (
      'en_proceso',
      'en_transito',
      'en_aduana',
      'entregado',
      'cancelado'
    )
  ),
  notas varchar(500)
);

create table clientes (
  id_cliente bigint primary key generated always as identity,
  nombre varchar(100) not null,
  apellido varchar(100) not null,
  ci varchar(20) unique not null,
  telefono varchar(20),
  email varchar(100) unique,
  direccion varchar(200),
  fecha_registro date default current_date
);

create table gerentes (
  id_gerente bigint primary key generated always as identity,
  nombre varchar(100) not null,
  apellido varchar(100) not null,
  cargo varchar(100),
  email varchar(100) unique not null,
  fecha_ingreso date default current_date
);

create table ventas (
  id_venta bigint primary key generated always as identity,
  id_cliente bigint not null references clientes (id_cliente),
  id_gerente bigint not null references gerentes (id_gerente),
  id_moto bigint not null references motos (id_moto),
  fecha_venta timestamp default current_timestamp,
  precio_final numeric(12, 2) not null,
  descuento_aplicado numeric(5, 2) default 0.00,
  metodo_pago varchar(30) check (
    metodo_pago in ('efectivo', 'tarjeta', 'transferencia', 'credito')
  ),
  notas varchar(500)
);

create table busquedas_clientes (
  id_busqueda bigint primary key generated always as identity,
  id_cliente bigint references clientes (id_cliente),
  id_moto bigint references motos (id_moto),
  fecha_busqueda timestamp default current_timestamp,
  marca_buscada varchar(50),
  modelo_buscado varchar(100),
  tipo_buscado varchar(50)
);

create index idx_motos_marca on motos using btree (marca);

create index idx_motos_modelo on motos using btree (modelo);

create index idx_motos_stock on motos using btree (stock);

create index idx_motos_estado on motos using btree (estado);

create index idx_importaciones_fecha on importaciones using btree (fecha_pedido);

create index idx_importaciones_estado on importaciones using btree (estado_envio);

create view vista_catalogo_clientes as
select
  m.id_moto,
  m.marca,
  m.modelo,
  m."año",
  m.tipo,
  m.cilindrada_cc,
  m.precio_venta,
  m.color,
  m.imagen_url,
  m.estado
from
  motos m
where
  m.estado = 'disponible'
order by
  m.marca,
  m.modelo;

create view vista_admin_catalogo_completo as
select
  m.*,
  d.nombre as distribuidor,
  d.tipo_relacion
from
  motos m
  join distribuidores d on m.id_distribuidor = d.id_distribuidor
order by
  m.marca,
  m.modelo;

create view vista_admin_importaciones as
select
  i.*,
  m.marca,
  m.modelo,
  d.nombre as distribuidor,
  d.tipo_relacion
from
  importaciones i
  join motos m on i.id_moto = m.id_moto
  join distribuidores d on i.id_distribuidor = d.id_distribuidor
order by
  i.fecha_pedido desc;

create view vista_admin_ventas as
select
  v.id_venta,
  v.fecha_venta,
  (c.nombre || ' ') || c.apellido as cliente,
  (g.nombre || ' ') || g.apellido as registrado_por,
  m.marca,
  m.modelo,
  v.precio_final,
  v.descuento_aplicado,
  v.metodo_pago
from
  ventas v
  join clientes c on v.id_cliente = c.id_cliente
  join gerentes g on v.id_gerente = g.id_gerente
  join motos m on v.id_moto = m.id_moto
order by
  v.fecha_venta desc;

create view vista_admin_busquedas as
select
  marca_buscada,
  modelo_buscado,
  count(*) as total_busquedas
from
  busquedas_clientes
group by
  marca_buscada,
  modelo_buscado
order by
  total_busquedas desc;

do $$
begin
  if not exists (select from pg_roles where rolname = 'rol_cliente') then
    create role rol_cliente;
  end if;
end;
$$;

do $$
begin
  if not exists (select from pg_roles where rolname = 'rol_admin') then
    create role rol_admin;
  end if;
end;
$$;

grant usage on schema ventas to rol_cliente;

grant select on vista_catalogo_clientes to rol_cliente;

grant usage on schema ventas to rol_admin;

grant all on all tables in schema ventas to rol_admin;

grant all on all sequences in schema ventas to rol_admin;

comment on table distribuidores is 'Proveedores internacionales de motocicletas.';

comment on table motos is 'Catálogo de motocicletas importadas disponibles para la venta.';

comment on table importaciones is 'Órdenes de importación de motos gestionadas a través de distribuidores.';

comment on table clientes is 'Clientes registrados en el sistema.';

comment on table gerentes is 'Personal administrativo autorizado para gestionar el sistema.';

comment on table ventas is 'Registro de ventas realizadas a clientes.';

comment on table busquedas_clientes is 'Historial de búsquedas realizadas por clientes en el catálogo.';

comment on view vista_catalogo_clientes is 'Catálogo visible para clientes: solo motos disponibles.';

comment on view vista_admin_catalogo_completo is 'Catálogo completo con datos del distribuidor.';

comment on view vista_admin_importaciones is 'Historial de importaciones con datos de moto y distribuidor.';

comment on view vista_admin_ventas is 'Historial de ventas con datos del cliente y administrador.';

comment on view vista_admin_busquedas is 'Reporte de búsquedas más frecuentes realizadas por clientes.';

-- =============================================================================
-- TRIGGERS: VENTAS
-- =============================================================================

create or replace function ventas.descontar_stock_venta()
returns trigger as $$
begin
  update ventas.motos set stock = stock - 1
  where id_moto = new.id_moto;
  return new;
end;
$$ language plpgsql;

create trigger trg_descontar_stock_venta
  after insert on ventas.ventas
  for each row
  execute function ventas.descontar_stock_venta();

-- =============================================================================
-- SCHEMA: CLIENTELA
-- =============================================================================

set search_path to clientela;

create table unidades_cliente (
  id_unidad bigint primary key generated always as identity,
  id_cliente_ref bigint not null references ventas.clientes (id_cliente),
  id_venta_ref bigint not null unique references ventas.ventas (id_venta),
  id_moto_ref bigint not null references ventas.motos (id_moto),
  numero_serie_moto varchar(50) not null unique,
  fecha_compra date not null,
  kilometraje_compra int default 0,
  kilometraje_actual int default 0,
  color varchar(50),
  placa_patente varchar(20),
  activo boolean default true
);

create table planes_mantenimiento (
  id_plan bigint primary key generated always as identity,
  nombre_plan varchar(100) not null,
  marca_aplica varchar(50),
  tipo_moto_aplica varchar(50),
  descripcion varchar(500),
  km_inicio int not null,
  km_fin int not null,
  es_gratuito boolean default false,
  incluye_cambio_aceite boolean default true,
  incluye_ajuste_frenos boolean default true,
  incluye_revision_cadena boolean default true,
  incluye_revision_motor boolean default true,
  incluye_revision_electrica boolean default false,
  incluye_revision_suspension boolean default false,
  costo_servicio numeric(10, 2) default 0.00,
  duracion_estimada_horas numeric(4, 2),
  activo boolean default true
);

create table servicios_mantenimiento (
  id_servicio bigint primary key generated always as identity,
  id_unidad bigint not null references unidades_cliente (id_unidad),
  id_plan bigint references planes_mantenimiento (id_plan),
  fecha_servicio timestamp default current_timestamp not null,
  kilometraje_entrada int not null,
  tipo_servicio varchar(50) not null check (tipo_servicio in ('programado', 'correctivo', 'garantia')),
  descripcion_trabajos varchar(500) not null,
  aceite_cambiado boolean default false,
  tipo_aceite_usado varchar(80),
  frenos_ajustados boolean default false,
  cadena_revisada boolean default false,
  motor_revisado boolean default false,
  electrico_revisado boolean default false,
  suspension_revisada boolean default false,
  observaciones_tecnicas varchar(500),
  costo_mano_obra numeric(10, 2) default 0.00,
  costo_repuestos numeric(10, 2) default 0.00,
  costo_total numeric(10, 2) default 0.00,
  es_gratuito boolean default false,
  proximo_servicio_km int,
  proximo_servicio_fecha date,
  tecnico_responsable varchar(100) not null,
  id_vendedor_ref bigint references ventas.gerentes (id_gerente),
  estado_servicio varchar(30) default 'completado' check (estado_servicio in ('pendiente', 'en_proceso', 'completado', 'cancelado'))
);

create table categorias_repuesto (
  id_categoria bigint primary key generated always as identity,
  nombre varchar(80) not null,
  descripcion varchar(500),
  activo boolean default true
);

create table repuestos (
  id_repuesto bigint primary key generated always as identity,
  id_categoria bigint not null references categorias_repuesto (id_categoria),
  codigo_repuesto varchar(50) unique not null,
  nombre varchar(150) not null,
  descripcion varchar(500),
  marca_compatible varchar(50),
  modelos_compatibles varchar(500),
  "año_desde" int,
  "año_hasta" int,
  precio_costo numeric(10, 2) not null,
  precio_venta numeric(10, 2) not null,
  stock_actual int not null default 0,
  stock_minimo int default 2,
  unidad_medida varchar(30),
  imagen_url text,
  es_repuesto_garantia boolean default false,
  activo boolean default true
);

create table repuestos_usados_en_servicio (
  id_uso_repuesto bigint primary key generated always as identity,
  id_servicio bigint not null references servicios_mantenimiento (id_servicio),
  id_repuesto bigint not null references repuestos (id_repuesto),
  cantidad_usada numeric(8, 2) not null,
  precio_unitario_cobrado numeric(10, 2) not null,
  subtotal numeric(10, 2) not null,
  cubierto_por_garantia boolean default false,
  cubierto_por_mantenimiento_gratis boolean default false
);

create table solicitudes_repuesto (
  id_solicitud bigint primary key generated always as identity,
  id_unidad bigint not null references unidades_cliente (id_unidad),
  id_repuesto bigint references repuestos (id_repuesto),
  descripcion_libre varchar(500),
  cantidad_solicitada int not null default 1,
  fecha_solicitud timestamp default current_timestamp,
  estado_solicitud varchar(30) default 'pendiente' check (estado_solicitud in ('pendiente', 'aprobada', 'rechazada', 'disponible')),
  fecha_disponibilidad date,
  notificado_cliente boolean default false,
  precio_cotizado numeric(10, 2),
  notas_taller varchar(500)
);

create table garantias_posventa (
  id_garantia_pos bigint primary key generated always as identity,
  id_unidad bigint not null unique references unidades_cliente (id_unidad),
  tipo_garantia_nombre varchar(100) not null,
  fecha_inicio date not null,
  fecha_fin date not null,
  cubre_motor boolean default true,
  cubre_transmision boolean default true,
  cubre_electrico boolean default false,
  cubre_carroceria boolean default false,
  estado_garantia varchar(30) default 'activa' check (estado_garantia in ('activa', 'expirada', 'cancelada')),
  kilometraje_maximo_garantia int,
  condiciones_especiales varchar(500)
);

create table reclamos_tecnicos (
  id_reclamo_tec bigint primary key generated always as identity,
  id_unidad bigint not null references unidades_cliente (id_unidad),
  id_garantia_pos bigint references garantias_posventa (id_garantia_pos),
  kilometraje_reclamo int not null,
  fecha_ingreso timestamp default current_timestamp,
  descripcion_cliente varchar(500) not null,
  componente_afectado varchar(100),
  diagnostico_tecnico varchar(500),
  causa_probable varchar(500),
  aplica_garantia boolean default false,
  accion_tomada varchar(50) check (accion_tomada in ('reparado', 'reemplazado', 'pendiente', 'sin_reparacion')),
  id_servicio_realizado bigint references servicios_mantenimiento (id_servicio),
  costo_reparacion numeric(10, 2) default 0.00,
  costo_cubierto_garantia numeric(10, 2) default 0.00,
  costo_cobrado_cliente numeric(10, 2) default 0.00,
  fecha_resolucion date,
  tecnico_responsable varchar(100),
  estado_reclamo varchar(30) default 'abierto' check (estado_reclamo in ('abierto', 'en_diagnostico', 'en_reparacion', 'resuelto', 'rechazado')),
  notas_internas varchar(500),
  requiere_repuesto_importado boolean default false
);

create table alertas_servicio (
  id_alerta bigint primary key generated always as identity,
  id_unidad bigint not null references unidades_cliente (id_unidad),
  tipo_alerta varchar(80) not null check (tipo_alerta in ('mantenimiento', 'garantia', 'repuesto', 'general')),
  mensaje varchar(500) not null,
  fecha_generacion timestamp default current_timestamp,
  fecha_envio timestamp,
  canal_envio varchar(50),
  estado_alerta varchar(30) default 'pendiente' check (estado_alerta in ('pendiente', 'enviada', 'cancelada')),
  km_referencia int,
  id_solicitud_repuesto bigint references solicitudes_repuesto (id_solicitud)
);

create table usuarios (
  id_usuario bigint primary key generated always as identity,
  username varchar(50) unique not null,
  password_hash text not null,
  rol varchar(20) not null check (rol in ('admin', 'cliente')),
  id_gerente bigint references ventas.gerentes (id_gerente),
  id_cliente bigint references ventas.clientes (id_cliente),
  activo boolean default true
);

create index idx_unidades_cliente on unidades_cliente using btree (id_cliente_ref);
create index idx_unidades_serie on unidades_cliente using btree (numero_serie_moto);
create index idx_unidades_venta on unidades_cliente using btree (id_venta_ref);
create index idx_servicios_unidad on servicios_mantenimiento using btree (id_unidad);
create index idx_servicios_fecha on servicios_mantenimiento using btree (fecha_servicio);
create index idx_servicios_km on servicios_mantenimiento using btree (kilometraje_entrada);
create index idx_servicios_tipo on servicios_mantenimiento using btree (tipo_servicio);
create index idx_servicios_gratuito on servicios_mantenimiento using btree (es_gratuito);
create index idx_repuestos_codigo on repuestos using btree (codigo_repuesto);
create index idx_repuestos_marca on repuestos using btree (marca_compatible);
create index idx_repuestos_categoria on repuestos using btree (id_categoria);
create index idx_repuestos_stock on repuestos using btree (stock_actual);
create index idx_solicitudes_unidad on solicitudes_repuesto using btree (id_unidad);
create index idx_solicitudes_estado on solicitudes_repuesto using btree (estado_solicitud);
create index idx_gpos_unidad on garantias_posventa using btree (id_unidad);
create index idx_gpos_estado on garantias_posventa using btree (estado_garantia);
create index idx_gpos_fecha_fin on garantias_posventa using btree (fecha_fin);
create index idx_reclamos_tec_unidad on reclamos_tecnicos using btree (id_unidad);
create index idx_reclamos_tec_estado on reclamos_tecnicos using btree (estado_reclamo);
create index idx_reclamos_tec_fecha on reclamos_tecnicos using btree (fecha_ingreso);
create index idx_reclamos_tec_componente on reclamos_tecnicos using btree (componente_afectado);
create index idx_alertas_unidad on alertas_servicio using btree (id_unidad);
create index idx_alertas_estado on alertas_servicio using btree (estado_alerta);
create index idx_alertas_tipo on alertas_servicio using btree (tipo_alerta);
create index idx_usuarios_username on usuarios using btree (username);
create index idx_usuarios_rol on usuarios using btree (rol);

-- =============================================================================
-- TRIGGERS: CLIENTELA
-- =============================================================================

create or replace function clientela.descontar_stock_repuesto()
returns trigger as $$
begin
  update clientela.repuestos set stock_actual = stock_actual - new.cantidad_usada
  where id_repuesto = new.id_repuesto;
  return new;
end;
$$ language plpgsql;

create trigger trg_descontar_stock_repuesto
  after insert on clientela.repuestos_usados_en_servicio
  for each row
  execute function clientela.descontar_stock_repuesto();

create or replace function clientela.generar_alerta_mantenimiento()
returns trigger as $$
begin
  if new.proximo_servicio_km is not null then
    insert into clientela.alertas_servicio
      (id_unidad, tipo_alerta, mensaje, estado_alerta, km_referencia)
    values (
      new.id_unidad,
      'mantenimiento',
      format('Próximo mantenimiento programado a los %s km', new.proximo_servicio_km),
      'pendiente',
      new.proximo_servicio_km
    );
  end if;
  return new;
end;
$$ language plpgsql;

create trigger trg_alerta_mantenimiento
  after insert on clientela.servicios_mantenimiento
  for each row
  execute function clientela.generar_alerta_mantenimiento();

create or replace function clientela.generar_alerta_garantia()
returns trigger as $$
begin
  insert into clientela.alertas_servicio
    (id_unidad, tipo_alerta, mensaje, estado_alerta, km_referencia)
  values (
    new.id_unidad,
    'garantia',
    format('Garantía %s vigente hasta %s', new.tipo_garantia_nombre, new.fecha_fin),
    'pendiente',
    new.kilometraje_maximo_garantia
  );
  return new;
end;
$$ language plpgsql;

create trigger trg_alerta_garantia
  after insert on clientela.garantias_posventa
  for each row
  execute function clientela.generar_alerta_garantia();

create or replace function clientela.alerta_repuesto_disponible()
returns trigger as $$
begin
  if new.estado_solicitud = 'disponible' and (old.estado_solicitud is distinct from 'disponible') then
    insert into clientela.alertas_servicio
      (id_unidad, tipo_alerta, mensaje, estado_alerta, id_solicitud_repuesto)
    values (
      new.id_unidad,
      'repuesto',
      format('El repuesto solicitado (solicitud #%s) está disponible', new.id_solicitud),
      'pendiente',
      new.id_solicitud
    );
  end if;
  return new;
end;
$$ language plpgsql;

create trigger trg_alerta_repuesto
  after update on clientela.solicitudes_repuesto
  for each row
  execute function clientela.alerta_repuesto_disponible();

grant usage on schema clientela to rol_admin;

grant all on all tables in schema clientela to rol_admin;

grant all on all sequences in schema clientela to rol_admin;
