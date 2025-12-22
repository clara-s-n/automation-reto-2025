# 📊 Análisis de Cobertura de Tests vs Flujos Críticos

**Fecha:** 22 de Diciembre, 2025  
**Branch:** Lucas_Pruebas  
**Repositorio:** clara-s-n/automation-reto-2025

---

## 🔴 RESUMEN EJECUTIVO

| Prioridad            | Flujos Requeridos | Flujos Cubiertos | Cobertura |
| -------------------- | ----------------- | ---------------- | --------- |
| **P1 - Críticos**    | 4                 | 1 parcial        | ~15%      |
| **P2 - Importantes** | 3                 | 1 parcial        | ~20%      |
| **P3 - Necesarios**  | 5                 | 2 parciales      | ~25%      |
| **TOTAL**            | **12**            | **~4 parciales** | **~20%**  |

---

## 🔴 FLUJOS CRÍTICOS (Prioridad 1)

### 1. GESTIÓN DE PLANILLA DE INGRESOS - FLUJO COMPLETO

| Paso                          | Requerido | Test Existente                              | Estado       |
| ----------------------------- | --------- | ------------------------------------------- | ------------ |
| Login admin                   | ✅        | `LoginTest`, `ExpoLoginTest`                | ✅ Cubierto  |
| Navegar a Administración      | ✅        | `ExpoHomeTest.administracionNavigationTest` | ✅ Cubierto  |
| Seleccionar/Crear año activo  | ✅        | ❌ No existe                                | 🔴 **FALTA** |
| Navegar a Ingresos            | ✅        | `ExpoHomeTest.ingresosNavigationTest`       | ✅ Cubierto  |
| Acceder a detalle de planilla | ✅        | `IngresoDetallesTest`                       | ✅ Parcial   |
| Agregar fila a planilla       | ✅        | `IngresoCrearFilaTest`                      | ⚠️ Falla     |
| Verificar totales             | ✅        | ❌ No existe                                | 🔴 **FALTA** |

**Assertions faltantes:**

- ❌ Verificar planilla creada exitosamente
- ❌ Verificar numeración automática consecutiva
- ❌ Verificar cálculo saldo pendiente (Precio - Pago Inicial)
- ❌ Verificar total general de planilla
- ❌ Verificar que no se aceptan valores negativos

**Cobertura:** ~30%

---

### 2. GESTIÓN DE PLANILLA DE EGRESOS - FLUJO COMPLETO

| Paso                      | Requerido | Test Existente                        | Estado       |
| ------------------------- | --------- | ------------------------------------- | ------------ |
| Login admin               | ✅        | Varios tests                          | ✅ Cubierto  |
| Navegar a Egresos         | ✅        | `ExpoHomeTest.egresosNavigationTest`  | ✅ Cubierto  |
| Verificar/Crear categoría | ✅        | `ExpoCategoryTest.crearCategoriaTest` | ✅ Cubierto  |
| Crear planilla egresos    | ✅        | ❌ No existe                          | 🔴 **FALTA** |
| Agregar filas con empresa | ✅        | `EgresosCrearEmpresaTest`             | ⚠️ Parcial   |
| Auto-numeración filas     | ✅        | ❌ No existe                          | 🔴 **FALTA** |
| Verificar totales         | ✅        | ❌ No existe                          | 🔴 **FALTA** |

**Assertions faltantes:**

- ❌ Verificar categoría asociada correctamente
- ❌ Verificar auto-incremento de número de fila
- ❌ Verificar cálculo saldo pendiente (Precio - Suma Pagos)
- ❌ Verificar que suma pagos no excede precio
- ❌ Verificar fila requiere empresa proveedora

**Cobertura:** ~20%

---

### 3. CÁLCULOS AUTOMÁTICOS - PRECISIÓN MATEMÁTICA

| Paso                                   | Requerido | Test Existente | Estado       |
| -------------------------------------- | --------- | -------------- | ------------ |
| Verificar saldo inicial                | ✅        | ❌ No existe   | 🔴 **FALTA** |
| Verificar saldo con múltiples pagos    | ✅        | ❌ No existe   | 🔴 **FALTA** |
| Verificar precisión decimales          | ✅        | ❌ No existe   | 🔴 **FALTA** |
| Verificar pagos parciales acumulativos | ✅        | ❌ No existe   | 🔴 **FALTA** |
| Verificar total general planilla       | ✅        | `TotalesTest`  | ⚠️ Falla     |

**Assertions faltantes:**

- ❌ Verificar saldo = Precio - Pagos
- ❌ Verificar decimales con 2 posiciones
- ❌ Verificar saldo nunca negativo
- ❌ Verificar abonado no excede precio
- ❌ Verificar redondeo correcto
- ❌ Verificar caso precio = 0

**Cobertura:** ~5%

---

### 4. SALDO POR EMPRESA - CONSOLIDACIÓN

| Paso                            | Requerido | Test Existente            | Estado       |
| ------------------------------- | --------- | ------------------------- | ------------ |
| Crear/Seleccionar empresa       | ✅        | `EgresosCrearEmpresaTest` | ✅ Parcial   |
| Crear planillas y asociar filas | ✅        | `ExpoSaldoEmpresaTest`    | ⚠️ Falla     |
| Navegar a detalle empresa       | ✅        | ❌ No existe              | 🔴 **FALTA** |
| Verificar consolidación saldo   | ✅        | ❌ No existe              | 🔴 **FALTA** |

**Assertions faltantes:**

- ❌ Verificar todas las filas asociadas aparecen
- ❌ Verificar saldos individuales por planilla
- ❌ Verificar saldo total empresa
- ❌ Verificar separación ingresos/egresos
- ❌ Verificar actualización automática al agregar fila

**Cobertura:** ~10%

---

## 🟡 FLUJOS IMPORTANTES (Prioridad 2)

### 5. GESTIÓN DE EMPRESAS

| Paso                               | Requerido | Test Existente             | Estado       |
| ---------------------------------- | --------- | -------------------------- | ------------ |
| Crear empresa                      | ✅        | `EgresosCrearEmpresaTest`  | ✅ Parcial   |
| Editar empresa                     | ✅        | `EgresosEditarEmpresaTest` | ⚠️ Falla     |
| Eliminar empresa sin transacciones | ✅        | ❌ No existe               | 🔴 **FALTA** |
| Eliminar empresa con transacciones | ✅        | ❌ No existe               | 🔴 **FALTA** |
| Validar nombre duplicado           | ✅        | ❌ No existe               | 🔴 **FALTA** |

**Cobertura:** ~25%

---

### 6. GESTIÓN DE AÑOS

| Paso                         | Requerido | Test Existente | Estado       |
| ---------------------------- | --------- | -------------- | ------------ |
| Crear año                    | ✅        | ❌ No existe   | 🔴 **FALTA** |
| Seleccionar año activo       | ✅        | ❌ No existe   | 🔴 **FALTA** |
| Aislamiento datos entre años | ✅        | ❌ No existe   | 🔴 **FALTA** |
| Validar año duplicado        | ✅        | ❌ No existe   | 🔴 **FALTA** |

**Cobertura:** 0%

---

### 7. AUTENTICACIÓN Y AUTORIZACIÓN

| Paso                           | Requerido | Test Existente               | Estado       |
| ------------------------------ | --------- | ---------------------------- | ------------ |
| Login exitoso                  | ✅        | `LoginTest`, `ExpoLoginTest` | ✅ Cubierto  |
| Logout                         | ✅        | `ExpoLoginTest.logoutTest`   | ✅ Cubierto  |
| Login credenciales incorrectas | ✅        | ❌ No existe                 | 🔴 **FALTA** |
| Restricción usuario normal     | ✅        | ❌ No existe                 | 🔴 **FALTA** |
| Acceso admin completo          | ✅        | ❌ No existe                 | 🔴 **FALTA** |

**Cobertura:** ~30%

---

## 🟢 FLUJOS NECESARIOS (Prioridad 3)

### 8. GESTIÓN DE USUARIOS (ADMIN)

| Paso                  | Requerido | Test Existente                                                   | Estado       |
| --------------------- | --------- | ---------------------------------------------------------------- | ------------ |
| Crear usuario         | ✅        | `ExpoCreationUsuarioTest`                                        | ✅ Cubierto  |
| Crear usuario admin   | ✅        | `ExpoCreationUsuarioTest.crearUsuarioConAdministradorActivoTest` | ✅ Cubierto  |
| Editar usuario        | ✅        | `ExpoEditionUsuarioTest`                                         | ✅ Cubierto  |
| Eliminar usuario      | ✅        | `ExpoDeleteUsuarioTest`                                          | ✅ Cubierto  |
| Validar email único   | ✅        | ❌ No existe                                                     | 🔴 **FALTA** |
| Validar formato email | ✅        | ❌ No existe                                                     | 🔴 **FALTA** |
| Validar contraseña    | ✅        | ❌ No existe                                                     | 🔴 **FALTA** |

**Cobertura:** ~50%

---

### 9. CREACIÓN Y GESTIÓN DE CATEGORÍAS

| Paso                             | Requerido | Test Existente                         | Estado       |
| -------------------------------- | --------- | -------------------------------------- | ------------ |
| Crear categoría                  | ✅        | `ExpoCategoryTest.crearCategoriaTest`  | ✅ Cubierto  |
| Editar categoría                 | ✅        | `ExpoCategoryTest.editarCategoriaTest` | ⚠️ @Ignore   |
| Eliminar categoría con planillas | ✅        | ❌ No existe                           | 🔴 **FALTA** |
| Eliminar categoría vacía         | ✅        | ❌ No existe                           | 🔴 **FALTA** |
| Validar nombre único             | ✅        | ❌ No existe                           | 🔴 **FALTA** |

**Cobertura:** ~25%

---

### 10. GENERACIÓN Y VISUALIZACIÓN DE TOTALES

| Paso                         | Requerido | Test Existente | Estado       |
| ---------------------------- | --------- | -------------- | ------------ |
| Ver totales consolidados     | ✅        | `TotalesTest`  | ⚠️ Falla     |
| Total ingresos               | ✅        | `TotalesTest`  | ⚠️ Falla     |
| Total egresos                | ✅        | `TotalesTest`  | ⚠️ Falla     |
| Balance (ingresos - egresos) | ✅        | `TotalesTest`  | ⚠️ Falla     |
| Totales por año              | ✅        | ❌ No existe   | 🔴 **FALTA** |

**Cobertura:** ~20%

---

### 11. REGISTRO DE MÚLTIPLES PAGOS EN UNA FILA

| Paso                           | Requerido | Test Existente | Estado       |
| ------------------------------ | --------- | -------------- | ------------ |
| Registrar pagos parciales      | ✅        | ❌ No existe   | 🔴 **FALTA** |
| Verificar saldo actualizado    | ✅        | ❌ No existe   | 🔴 **FALTA** |
| Historial de pagos             | ✅        | ❌ No existe   | 🔴 **FALTA** |
| Validar pago > saldo           | ✅        | ❌ No existe   | 🔴 **FALTA** |
| Estado "Pagado" cuando saldo=0 | ✅        | ❌ No existe   | 🔴 **FALTA** |

**Cobertura:** 0%

---

### 12. EXPORTACIÓN/GENERACIÓN DE INFORMES

| Paso                | Requerido | Test Existente | Estado       |
| ------------------- | --------- | -------------- | ------------ |
| Generar informe     | ✅        | ❌ No existe   | 🔴 **FALTA** |
| Descargar archivo   | ✅        | ❌ No existe   | 🔴 **FALTA** |
| Verificar contenido | ✅        | ❌ No existe   | 🔴 **FALTA** |

**Cobertura:** 0%

---

## 📋 TESTS EXISTENTES VS FLUJOS

| Test Existente             | Flujos que Cubre              | Estado   |
| -------------------------- | ----------------------------- | -------- |
| `LoginTest`                | F7 (Autenticación)            | ✅ Pasa  |
| `ExpoLoginTest`            | F7 (Login + Logout)           | ✅ Pasa  |
| `ExpoHomeTest`             | F1, F2 (Navegación)           | ✅ Pasa  |
| `ExpoAdministrationTest`   | F6, F8, F9 (Navegación admin) | ✅ Pasa  |
| `IngresoDetallesTest`      | F1 (Ver detalle)              | ✅ Pasa  |
| `IngresoCrearFilaTest`     | F1 (Crear fila)               | ❌ Falla |
| `IngresoEliminarFilaTest`  | F1 (Eliminar fila)            | ❌ Falla |
| `EgresosCrearEmpresaTest`  | F5 (Crear empresa)            | ✅ Pasa  |
| `EgresosEditarEmpresaTest` | F5 (Editar empresa)           | ❌ Falla |
| `ExpoCreationUsuarioTest`  | F8 (Crear usuario)            | ✅ Pasa  |
| `ExpoEditionUsuarioTest`   | F8 (Editar usuario)           | ✅ Pasa  |
| `ExpoDeleteUsuarioTest`    | F8 (Eliminar usuario)         | ✅ Pasa  |
| `ExpoCategoryTest`         | F9 (Crear categoría)          | ✅ Pasa  |
| `TotalesTest`              | F10 (Totales)                 | ❌ Falla |
| `ExpoSaldoEmpresaTest`     | F4 (Saldo empresa)            | ❌ Falla |

---

## 🚨 TESTS FALTANTES CRÍTICOS

### Alta Prioridad (Deben implementarse primero)

1. **`AnioGestionTest`** - Flujo 6

   - Crear año
   - Seleccionar año activo
   - Aislamiento de datos entre años

2. **`PlanillaIngresosCompletoTest`** - Flujo 1

   - Flujo completo desde crear año hasta verificar totales
   - Validaciones de cálculos
   - Validaciones de valores negativos

3. **`PlanillaEgresosCompletoTest`** - Flujo 2

   - Flujo completo de egresos
   - Auto-numeración de filas
   - Asociación con categorías

4. **`CalculosAutomaticosTest`** - Flujo 3

   - Precisión matemática
   - Decimales
   - Múltiples pagos

5. **`LoginNegativoTest`** - Flujo 7
   - Credenciales incorrectas
   - Restricción de acceso usuario normal

### Media Prioridad

6. **`MultiplesPagosTest`** - Flujo 11

   - Pagos parciales
   - Historial
   - Validaciones

7. **`EmpresaConsolidacionTest`** - Flujo 4 (mejorar `ExpoSaldoEmpresaTest`)

   - Consolidación completa
   - Navegación a detalle empresa

8. **`EmpresaValidacionesTest`** - Flujo 5
   - Eliminar con/sin transacciones
   - Nombre duplicado

### Baja Prioridad

9. **`InformesExportacionTest`** - Flujo 12
   - Generación de reportes
   - Descarga de archivos

---

## 📈 RECOMENDACIONES

### Inmediatas (Sprint actual)

1. ✅ Corregir tests que fallan: `IngresoCrearFilaTest`, `IngresoEliminarFilaTest`, `TotalesTest`, `EgresosEditarEmpresaTest`, `ExpoSaldoEmpresaTest`
2. 🆕 Implementar `AnioGestionTest` - es prerequisito para casi todos los flujos

### Corto plazo (1-2 sprints)

3. 🆕 Implementar tests de cálculos automáticos (Flujo 3)
4. 🆕 Implementar tests de autenticación negativa (Flujo 7)
5. 🆕 Completar flujos de planillas (Flujos 1 y 2)

### Mediano plazo

6. 🆕 Tests de múltiples pagos (Flujo 11)
7. 🆕 Tests de consolidación empresa (Flujo 4)
8. 🆕 Tests de validaciones de negocio

---

## 📊 MATRIZ DE COBERTURA VISUAL

```
FLUJOS CRÍTICOS (P1)
━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
F1-Ingresos     [████░░░░░░░░░░░░░░░░] 30%
F2-Egresos      [████░░░░░░░░░░░░░░░░] 20%
F3-Cálculos     [█░░░░░░░░░░░░░░░░░░░]  5%
F4-Consolidación[██░░░░░░░░░░░░░░░░░░] 10%

FLUJOS IMPORTANTES (P2)
━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
F5-Empresas     [█████░░░░░░░░░░░░░░░] 25%
F6-Años         [░░░░░░░░░░░░░░░░░░░░]  0%
F7-Auth         [██████░░░░░░░░░░░░░░] 30%

FLUJOS NECESARIOS (P3)
━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
F8-Usuarios     [██████████░░░░░░░░░░] 50%
F9-Categorías   [█████░░░░░░░░░░░░░░░] 25%
F10-Totales     [████░░░░░░░░░░░░░░░░] 20%
F11-Pagos       [░░░░░░░░░░░░░░░░░░░░]  0%
F12-Informes    [░░░░░░░░░░░░░░░░░░░░]  0%
```

---

**Generado automáticamente por análisis de cobertura**
