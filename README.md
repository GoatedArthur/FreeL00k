# FreeL00k

A 360-degree freelook mod for Minecraft Forge 1.8.9.

Hold the freelook key (Left Alt by default) to freely rotate your camera without changing your movement direction. Works in both third-person back and front perspectives.

## Features

- 360-degree camera rotation (configurable max yaw: 90 / 180 / 360)
- Full vertical rotation (no pitch limit)
- Hold or toggle mode
- Configurable default perspective (keep current / third-person back / third-person front)
- Config GUI accessible from the Forge mod list
- Invert mouse support
- Camera starts level (pitch = 0) facing your current direction

## Installation

1. Install Minecraft Forge for 1.8.9
2. Download the latest `.jar` from Releases
3. Place it in your `.minecraft/mods` folder

## Controls

| Action | Default Key |
|--------|-------------|
| Freelook | Left Alt |

Rebindable in Controls > FreeL00k.

## Configuration

Access through the Forge mod list screen. Options:

- **Perspective**: Keep Current / Third Person (Back) / Third Person (Front)
- **Mode**: Hold (hold key to freelook) / Toggle (press to toggle)
- **Max Yaw**: 90 / 180 / 360 degrees

## Building from source

```bash
gradlew build --no-daemon
```

The compiled jar will be in `build/libs/FreeL00k-1.0.0.jar`.

## Credits

- **Arthur** -- Developer
- Reference: [Celibistrial/freelook](https://github.com/Celibistrial/freelook) (Fabric), [Atharva-Tiwari12/Freelook-Mod](https://github.com/Atharva-Tiwari12/Freelook-Mod) (Forge 1.8.9)
