function optional(accessor) {
    return {
        map: (mapping) => optional(mapping(accessor())),
        orElse: (value) => { try { return accessor() } catch(e) { return value } }
    }
}

export {optional}
